package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.dto.UserRequestDTO;
import com.tekstil.textile_management_system.dto.UserResponseDTO;
import com.tekstil.textile_management_system.dto.UserUpdateDTO;
import com.tekstil.textile_management_system.entity.PasswordResetToken;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.exception.AlreadyExistsException;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.mapper.UserMapper;
import com.tekstil.textile_management_system.repository.ModelStageHistoryRepository;
import com.tekstil.textile_management_system.repository.PasswordTokenRepository;
import com.tekstil.textile_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ModelStageHistoryRepository modelStageHistoryRepository;



    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException("User already exists: " + dto.getEmail());
        }
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // ekle
        User saved = userRepository.save(user);
        return UserMapper.toResponseDTO(saved);
    }

    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    public UserResponseDTO findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public List<UserResponseDTO> getUsersByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public List<UserResponseDTO> findByActive() {
        return userRepository.findByActiveTrue()
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public List<UserResponseDTO> findByFullNameContainingIgnoreCase(String fullName) {
        return userRepository.findByFullNameContainingIgnoreCase(fullName)
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public List<UserResponseDTO> findByCreatedAtAfter(LocalDateTime localDateTime) {
        return userRepository.findByCreatedAtAfter(localDateTime)
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        existing.setEmail(dto.getEmail());
        existing.setFullName(dto.getFullName());
        existing.setRole(dto.getRole());
        if (dto.getActive() != null) existing.setActive(dto.getActive());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return UserMapper.toResponseDTO(userRepository.save(existing));
    }




    public void deleteUser(Long id, UserDetails userDetails) throws BadRequestException {
        User caller = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (caller.getId().equals(id)) {
            throw new BadRequestException("you cannot delete yourself.");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        boolean hasHistory = modelStageHistoryRepository.existsByAssignedUser(user);

        if (hasHistory) {
            throw new BadRequestException("this user has pasr records, you cannot delete this user. you can make that deactive.");
        }

        passwordTokenRepository.deleteByUser(user);
        userRepository.delete(user);
    }

    public UserResponseDTO deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        user.setActive(false);
        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        passwordTokenRepository.findByUser(user)
                .ifPresent(passwordTokenRepository::delete);
        passwordTokenRepository.flush();
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    public User findEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordTokenRepository.findByToken(token)
                .orElse(null);

        if (passToken == null) return "Geçersiz token.";
        if (passToken.getExpiryDate().before(new Date())) return "Token süresi dolmuş.";

        return null;
    }

    public void changePasswordByToken(String token, String newPassword) {
        PasswordResetToken passToken = passwordTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token bulunamadı."));

        User user = passToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordTokenRepository.delete(passToken);
    }

}