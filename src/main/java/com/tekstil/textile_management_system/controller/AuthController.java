package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.dto.AuthResponse;
import com.tekstil.textile_management_system.dto.LoginRequest;
import com.tekstil.textile_management_system.dto.RegisterRequest;
import com.tekstil.textile_management_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @RequestMapping("/api/auth/login")
    @PostMapping
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return  ResponseEntity.ok(authService.login(request));
    }
}
