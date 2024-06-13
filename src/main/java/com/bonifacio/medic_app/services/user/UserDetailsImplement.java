package com.bonifacio.medic_app.services.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bonifacio.medic_app.controller.dtos.auth.AuthCreateUserRequest;
import com.bonifacio.medic_app.controller.dtos.auth.AuthRequest;
import com.bonifacio.medic_app.mappers.IUserMapper;
import com.bonifacio.medic_app.persitence.entities.RoleEntity;
import com.bonifacio.medic_app.persitence.entities.UserEntity;
import com.bonifacio.medic_app.persitence.repositories.IRoleRepository;
import com.bonifacio.medic_app.persitence.repositories.IUserRepository;
import com.bonifacio.medic_app.responses.AuthRespose;
import com.bonifacio.medic_app.utils.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@Qualifier("user_service")
@AllArgsConstructor
public class UserDetailsImplement implements UserDetailsService,IUserService {
    @Autowired
    private final JwtUtils jwtUtils;
    @Autowired
    private final IUserRepository userRepository;
    @Autowired
    private final IRoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final IUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
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

    public AuthRespose registerUser(AuthCreateUserRequest authCreateUserRequest){
        List<String> rolesRequest = authCreateUserRequest.getRoles().getRoles();
        Set<RoleEntity> roles = new HashSet<>(this.roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest));
        if(roles.isEmpty()){
            throw new IllegalArgumentException("los roles no existen");
        }

        UserEntity userEntity = mapper.authCreateUserToUserEntity(authCreateUserRequest);
        userEntity.setPassword(passwordEncoder.encode(authCreateUserRequest.getPassword()));
        userEntity.setRoles(roles);
        UserEntity user = this.userRepository.save(userEntity);

        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
        String token  = this.jwtUtils.generateToken(authentication);

        return AuthRespose.builder()
            .jwt(token)
            .message("Usuario Creado Exitosamente")
            .status(true)
            .username(user.getUsername())
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