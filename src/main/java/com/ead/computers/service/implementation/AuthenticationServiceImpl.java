package com.ead.computers.service.implementation;

import com.ead.computers.dao.request.SignInRequest;
import com.ead.computers.dao.request.SignUpRequest;
import com.ead.computers.dao.response.JwtAuthenticationResponse;
import com.ead.computers.entities.*;
import com.ead.computers.repository.CustomerRepository;
import com.ead.computers.repository.DeliverRepository;
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
    private final CustomerRepository customerRepository;
    private final DeliverRepository deliverRepository;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile()).address(request.getAddress())
                .role(Role.USER).build();
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setMobile(request.getMobile());
        customer.setAddress(request.getAddress());
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);
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

    @Override
    public JwtAuthenticationResponse adminSignup(SignUpRequest request) {
        var admin = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile()).address(request.getAddress())
                .role(Role.ADMIN).build();
        admin.setCreatedAt(LocalDateTime.now());
        userRepository.save(admin);
        var jwt = jwtServise.generateToken(admin);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse deliverSignup(SignUpRequest request) {
        var deliver = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile()).address(request.getAddress())
                .role(Role.DELI).build();
        deliver.setCreatedAt(LocalDateTime.now());
        userRepository.save(deliver);
        Deliver deliver1 = new Deliver();
        deliver1.setName(request.getFirstName() + " " + request.getLastName());
        deliver1.setPhone(Integer.parseInt(request.getMobile()));
        deliverRepository.save(deliver1);
        var jwt = jwtServise.generateToken(deliver);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
