package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.exception.AlreadyExistsException;
import com.tekstil.textile_management_system.exception.ResourceNotFoundException;
import com.tekstil.textile_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new AlreadyExistsException("User already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }
    public List<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findByActive(){
        return userRepository.findByActiveTrue();
    }
    public List<User> findByFullNameContainingIgnoreCase(String fullName){
        return userRepository.findByFullNameContainingIgnoreCase(fullName);
    }
    public List<User> findByCreatedAtAfter(LocalDateTime localDateTime){
        return userRepository.findByCreatedAtAfter(localDateTime);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found: " + id));
    }

    public List <User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public User updateUser(Long id, User updateUser) {
        User existing = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found : " + id));

        existing.setEmail(updateUser.getEmail());
        existing.setFullName(updateUser.getFullName());
        existing.setRole(updateUser.getRole());
        existing.setActive(updateUser.getActive());

        return userRepository.save(existing);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByEmailSingle(String email) {
        return findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
