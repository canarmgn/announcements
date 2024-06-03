package org.can.armagan.announcements.auth.service;


import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.auth.jwt.JwtUtil;
import org.can.armagan.announcements.auth.model.request.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public String getToken(AuthRequest authRequest) {
        try{
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())));
        } catch (BadCredentialsException ex){
            throw new BadCredentialsException("Incorrect username or password", ex);
        }
        final UserDetails userDetails =userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

}
