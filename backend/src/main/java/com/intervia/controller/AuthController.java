package com.intervia.controller;

import com.intervia.dto.UserResponse;
import com.intervia.dto.LoginRequest;
import com.intervia.dto.LoginResponse;
import com.intervia.dto.RegisterRequest;
import com.intervia.entity.User;
import com.intervia.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = authService.login(request);

        return ResponseEntity.ok(new LoginResponse(user));
    }
}