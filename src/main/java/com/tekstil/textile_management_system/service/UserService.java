package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.dto.UserRequestDTO;
import com.tekstil.textile_management_system.dto.UserResponseDTO;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.exception.AlreadyExistsException;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.mapper.UserMapper;
import com.tekstil.textile_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException("User already exists: " + dto.getEmail());
        }
        User saved = userRepository.save(UserMapper.toEntity(dto));
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

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        existing.setEmail(dto.getEmail());
        existing.setFullName(dto.getFullName());
        existing.setRole(dto.getRole());
        return UserMapper.toResponseDTO(userRepository.save(existing));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }
}