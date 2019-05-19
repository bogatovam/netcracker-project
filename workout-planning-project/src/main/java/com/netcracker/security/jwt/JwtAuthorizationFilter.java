package com.netcracker.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.netcracker.model.documents.User;
import com.netcracker.repository.documents.UserRepository;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.security.details.UserPrincipalDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Data
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // Read the Authorization header, where the JWT token should be
            String header = request.getHeader(JwtTokenProvider.JwtProperties.HEADER_STRING);
            // If header does not contain BEARER or is null delegate to Spring impl and exit
            if (header == null || !header.startsWith(JwtTokenProvider.JwtProperties.TOKEN_PREFIX)) {
                System.out.println("1");
                chain.doFilter(request, response);
                System.out.println("2");

                return;
            }

            // If header is present, try grab user principal from database and perform authorization
            Authentication authentication = getUsernamePasswordAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }
        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenProvider.JwtProperties.HEADER_STRING)
                .replace(JwtTokenProvider.JwtProperties.TOKEN_PREFIX, "");

        if (token != null && tokenProvider.validateJwtToken(token)) {
            // parse the token and validate it
            String userName = tokenProvider.getUserNameFromJwtToken(token);

            // Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
            if (userName != null) {
                UserPrincipal principal =(UserPrincipal)userPrincipalDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
