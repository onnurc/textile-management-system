package com.tekstil.textile_management_system.repository;

import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByActiveTrue();
    List<User> findByFullNameContainingIgnoreCase(String fullName);
    List<User> findByCreatedAtAfter(LocalDateTime date);

    boolean existsByEmail(String email);

}