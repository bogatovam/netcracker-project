package com.netcracker.security.jwt;

import com.netcracker.repository.documents.UserRepository;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.security.details.UserPrincipalDetailsService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            String header = request.getHeader(JwtTokenProvider.JwtProperties.HEADER_STRING);

            if (header == null || !header.startsWith(JwtTokenProvider.JwtProperties.TOKEN_PREFIX)) {
                logger.info("Header doesnt starts with " + JwtTokenProvider.JwtProperties.TOKEN_PREFIX
                        + " or contain " + JwtTokenProvider.JwtProperties.HEADER_STRING);
                chain.doFilter(request, response);
                return;
            }
            logger.info("Header is " + header);

            Authentication authentication = getUsernamePasswordAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RuntimeException e) {
            logger.error("Can not set user authentication ", e);
        }
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {

        String authHeader = request.getHeader(JwtTokenProvider.JwtProperties.HEADER_STRING);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replaceAll(JwtTokenProvider.JwtProperties.TOKEN_PREFIX, "");
            String userName = tokenProvider.getUserNameFromJwtToken(token);

            if (userName != null) {
                UserPrincipal principal = (UserPrincipal) userPrincipalDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal.getUser().getId(), null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
