package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.dto.UserRequestDTO;
import com.tekstil.textile_management_system.dto.UserResponseDTO;
import com.tekstil.textile_management_system.dto.UserUpdateDTO;
import com.tekstil.textile_management_system.enums.Role;
import com.tekstil.textile_management_system.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('COMPANY_MANAGER')")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated() and !hasRole('PENDING')")
    public ResponseEntity<BaseResponse<UserResponseDTO>> getMyProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDTO user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(BaseResponse.success(200, "Current user", user));
    }

    @GetMapping("/my-next-stage")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponse<List<UserResponseDTO>>> getNextStageUsers(
            @AuthenticationPrincipal UserDetails userDetails) {

        UserResponseDTO caller = userService.findByEmail(userDetails.getUsername());
        Role callerRole = caller.getRole();

        if (!callerRole.hasNextRole()) {
            throw new AccessDeniedException("Bu rolün atama yapma yetkisi yok");
        }

        List<UserResponseDTO> users = userService.getUsersByRole(callerRole.getNextRole());
        return ResponseEntity.ok(BaseResponse.success(200, "Next stage users", users));
    }
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
            @PathVariable @Min(1) Long id, @RequestBody @Valid UserUpdateDTO dto) {
        UserResponseDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(BaseResponse.success(200, "User updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(
            @PathVariable @Min(1) Long id,
            @AuthenticationPrincipal UserDetails userDetails) throws BadRequestException {
        userService.deleteUser(id, userDetails);
        return ResponseEntity.ok(BaseResponse.success(200, "User deleted", null));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<BaseResponse<UserResponseDTO>> deactivateUser(@PathVariable @Min(1) Long id) {
        UserResponseDTO updated = userService.deactivateUser(id);
        return ResponseEntity.ok(BaseResponse.success(200, "User deactivated", updated));
    }
}