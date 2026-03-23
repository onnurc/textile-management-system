package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.UserRequestDTO;
import com.tekstil.textile_management_system.dto.UserResponseDTO;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<UserResponseDTO>> createUser(
            @RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(HttpStatus.CREATED.value(), "User created", created));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(BaseResponse.success(200, "Users listed", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponseDTO>> getUserById(@PathVariable @Min(1) Long id) {
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(BaseResponse.success(200, "User found", user));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> getUserByRole(@PathVariable Role role) {
        List<UserResponseDTO> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(BaseResponse.success(200, "Users with role " + role, users));
    }

    @GetMapping("/active")
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> getActiveUsers() {
        List<UserResponseDTO> users = userService.findByActive();
        return ResponseEntity.ok(BaseResponse.success(200, "Active users listed", users));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BaseResponse<UserResponseDTO>> findByEmail(@PathVariable String email) {
        UserResponseDTO user = userService.findByEmail(email);
        return ResponseEntity.ok(BaseResponse.success(200, "User found", user));
    }

    @GetMapping("/created-after")
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> findByCreatedAtAfter(
            @RequestParam LocalDateTime localDateTime) {
        List<UserResponseDTO> users = userService.findByCreatedAtAfter(localDateTime);
        return ResponseEntity.ok(BaseResponse.success(200, "Users listed", users));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> findByFullNameContaining(
            @PathVariable String keyword) {
        List<UserResponseDTO> users = userService.findByFullNameContainingIgnoreCase(keyword);
        return ResponseEntity.ok(BaseResponse.success(200, "Users matching: " + keyword, users));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponseDTO>> updateUser(
            @PathVariable @Min(1) Long id, @RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(BaseResponse.success(200, "User updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable @Min(1) Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(BaseResponse.success(200, "User deleted", null));
    }
}