package com.netcracker.services.impl;

import com.netcracker.model.documents.User;
import com.netcracker.model.view.request.SignUpRequest;
import com.netcracker.model.view.response.JwtResponse;
import com.netcracker.model.view.response.ResponseMessage;
import com.netcracker.repository.documents.UserRepository;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.security.jwt.JwtAuthorizationFilter;
import com.netcracker.security.jwt.JwtTokenProvider;
import com.netcracker.services.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public ResponseEntity<?> signIn(String login, String password) {
        logger.info("Start authentication for " + login);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String jwt = tokenProvider.generateToken(authentication);
        logger.info("Authentication for " + login + " was successful");
        logger.info("Authentication return " +
                (new JwtResponse(jwt, userPrincipal.getUsername(), userPrincipal.getAuthorities())).toString());

        return ResponseEntity.ok(new JwtResponse(jwt, userPrincipal.getUsername(), userPrincipal.getAuthorities()));
    }

    @Override
    public ResponseEntity<?> signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = User.builder()
                .fullName(signUpRequest.getName())
                .login(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(signUpRequest.getRole().toString());

        User result = userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
