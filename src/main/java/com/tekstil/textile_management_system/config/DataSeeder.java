package com.tekstil.textile_management_system.config;

import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(ApplicationArguments args) {
        if (!userRepository.existsByRole(Role.COMPANY_MANAGER)) {
            User manager = new User();
            manager.setEmail(adminEmail);
            manager.setPassword(passwordEncoder.encode(adminPassword));
            manager.setFullName("System Admin");
            manager.setRole(Role.COMPANY_MANAGER);
            manager.setActive(true);
            manager.setCreatedAt(LocalDateTime.now());
            userRepository.save(manager);
            System.out.println("✅ Default manager created: " + adminEmail);
        }
    }
}