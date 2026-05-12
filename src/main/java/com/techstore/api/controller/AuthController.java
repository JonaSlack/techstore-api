package com.techstore.api.controller;

import com.techstore.api.dto.LoginRequest;
import com.techstore.api.dto.LoginResponse;
import com.techstore.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if ("admin@techstore.cl".equals(request.getUsername())
                && "Admin1234".equals(request.getPassword())) {

            String token = jwtUtil.generarToken(request.getUsername());

            return ResponseEntity.ok(
                    new LoginResponse(token, "Bearer", "3600")
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales incorrectas");
    }
}

