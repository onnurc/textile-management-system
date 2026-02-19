package com.tekstil.textile_management_system.service;

import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
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
            throw new RuntimeException("User already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public List<User> findByRole(Role role){
        return userRepository.findByRole(role);
    }
    public List<User> findByActive(){
        return userRepository.findByActiveTrue();
    }
    public List<User> findByFullNameContainingIgnoreCase(String fullName){
        return userRepository.findByFullNameContainingIgnoreCase(fullName);
    }
    List<User> findByCreatedAtAfter(LocalDateTime localDateTime){
        return userRepository.findByCreatedAtAfter(localDateTime);
    }




}
