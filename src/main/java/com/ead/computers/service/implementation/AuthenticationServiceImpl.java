package com.ead.computers.service.implementation;

import com.ead.computers.dao.request.SignInRequest;
import com.ead.computers.dao.request.SignUpRequest;
import com.ead.computers.dao.response.JwtAuthenticationResponse;
import com.ead.computers.entities.Role;
import com.ead.computers.entities.User;
import com.ead.computers.repository.UserRepository;
import com.ead.computers.service.AuthenticationServise;
import com.ead.computers.service.JwtServise;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationServise {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServise jwtServise;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile()).address(request.getAddress())
                .role(Role.USER).build();
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        var jwt = jwtServise.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtServise.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
