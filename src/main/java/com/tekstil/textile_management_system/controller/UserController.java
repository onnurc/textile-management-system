package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.UserRequestDTO;
import com.tekstil.textile_management_system.dto.UserResponseDTO;
import com.tekstil.textile_management_system.entity.User;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<BaseResponse<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {


        UserResponseDTO created = userService.createUser(userRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "User created", created));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No users found", users));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Users listed", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No user found with id: " + id));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "User found", user.get()));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<BaseResponse<List<User>>> getUserByRole(@PathVariable Role role) {
        List<User> users = userService.getUsersByRole(role);

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No users found with role: " + role, users));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Users with role " + role, users));
    }

    @GetMapping("/active")
    public ResponseEntity<BaseResponse<List<User>>> getActiveUsers() {
        List<User> users = userService.findByActive();

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No active users found", users));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Active users listed", users));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BaseResponse<List<User>>> findByEmail(@PathVariable String email) {
        if (email == null || email.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "Email cannot be empty"));
        }

        List<User> users = userService.findByEmail(email);

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No users found with email: " + email));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Users found", users));
    }

    @GetMapping("/created-after")
    public ResponseEntity<BaseResponse<List<User>>> findByCreatedAtAfter(@RequestParam LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "Date parameter cannot be null"));
        }

        List<User> users = userService.findByCreatedAtAfter(localDateTime);

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No users found after: " + localDateTime, users));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Users created after " + localDateTime, users));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<BaseResponse<List<User>>> findByFullNameContaining(@PathVariable String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "Keyword cannot be empty"));
        }

        List<User> users = userService.findByFullNameContainingIgnoreCase(keyword);

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(BaseResponse.success(HttpStatus.NO_CONTENT.value(), "No users found for keyword: " + keyword, users));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "Users matching: " + keyword, users));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> existing = userService.findById(id);

        if (existing.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No user found with id: " + id));
        }

        User updated = userService.updateUser(id, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "User updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable Long id) {
        Optional<User> existing = userService.findById(id);

        if (existing.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(HttpStatus.NOT_FOUND.value(), "No user found with id: " + id));
        }

        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.success(HttpStatus.OK.value(), "User deleted successfully", null));
    }
}