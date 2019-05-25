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
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Data
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // Read the Authorization header, where the JWT token should be
            logger.info("Read the Authorization header, where the JWT token should be");
            String header = request.getHeader(JwtTokenProvider.JwtProperties.HEADER_STRING);
            // If header does not contain BEARER or is null delegate to Spring impl and exit
           // logger.info(request.getHeader("Access-Control-Request-Headers"));
            logger.info("Header is " + header);
            if (header == null || !header.startsWith(JwtTokenProvider.JwtProperties.TOKEN_PREFIX)) {
                logger.info("Header doesnt contain " + JwtTokenProvider.JwtProperties.TOKEN_PREFIX
                        + " or " + JwtTokenProvider.JwtProperties.HEADER_STRING);
                chain.doFilter(request, response);
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
        String authHeader = request.getHeader(JwtTokenProvider.JwtProperties.HEADER_STRING);
        logger.info("Authentication header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replaceAll(JwtTokenProvider.JwtProperties.TOKEN_PREFIX, "");

            // this is  marker for dubug
            logger.info("Provider : " + tokenProvider);
            logger.info("Repository : " + userPrincipalDetailsService);

            logger.info("Token: " + token);

            if (token != null ) {
                logger.info("Validation of this token is success!");

                // parse the token and validate it
                String userName = tokenProvider.getUserNameFromJwtToken(token);
                logger.info("User name: " +userName);

                // Search in the DB if we find the user by token subject (username)
                // If so, then grab user details and create spring auth token using username, pass, authorities/roles
                if (userName != null) {
                    UserPrincipal principal = (UserPrincipal) userPrincipalDetailsService.loadUserByUsername(userName);
                    logger.info("User principal: " + principal.toString());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal.getUser().getId(), null, principal.getAuthorities());
                    logger.info("User auth: " + auth);
                    return auth;
                }
                return null;
            }
        }
        return null;
    }
}
