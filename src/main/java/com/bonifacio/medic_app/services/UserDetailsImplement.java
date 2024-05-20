package com.bonifacio.medic_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bonifacio.medic_app.controller.dtos.auth.AuthRequest;
import com.bonifacio.medic_app.persitence.entities.UserEntity;
import com.bonifacio.medic_app.persitence.repositories.IRoleRepository;
import com.bonifacio.medic_app.persitence.repositories.IUserRepository;
import com.bonifacio.medic_app.responses.AuthRespose;
import com.bonifacio.medic_app.utils.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@Qualifier("user_service")
@AllArgsConstructor
public class UserDetailsImplement implements UserDetailsService {
    @Autowired
    private final JwtUtils jwtUtils;
    @Autowired
    private final IUserRepository userRepository;
    @Autowired
    private final IRoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow(null);
        
        return userEntity;
    }

    public AuthRespose loginUser(AuthRequest authRequest){

        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        Authentication authentication = this.authentication(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = this.jwtUtils.generateToken(authentication);

        return AuthRespose.builder()
        .jwt(token)
        .message("el usuario se a logeado correctamente")
        .status(true)
        .build();
    }

    private Authentication authentication(String username,String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
    }
}