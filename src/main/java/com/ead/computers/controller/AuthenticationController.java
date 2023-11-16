package com.ead.computers.controller;

import com.ead.computers.dao.request.SignInRequest;
import com.ead.computers.dao.request.SignUpRequest;
import com.ead.computers.dao.response.JwtAuthenticationResponse;
import com.ead.computers.service.AuthenticationServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServise authenticationServise;

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationServise.signup(request));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationServise.signin(request));
    }

    @PostMapping("/adminSignup")
    public ResponseEntity<JwtAuthenticationResponse> adminSignup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationServise.adminSignup(request));
    }

    @PostMapping("/deliverSignup")
    public ResponseEntity<JwtAuthenticationResponse> deliverSignup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationServise.deliverSignup(request));
    }
}
