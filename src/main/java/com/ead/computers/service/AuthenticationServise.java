package com.ead.computers.service;

import com.ead.computers.dao.request.SignInRequest;
import com.ead.computers.dao.request.SignUpRequest;
import com.ead.computers.dao.response.JwtAuthenticationResponse;

public interface AuthenticationServise {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
    JwtAuthenticationResponse adminSignup(SignUpRequest request);

    JwtAuthenticationResponse deliverSignup(SignUpRequest request);
}
