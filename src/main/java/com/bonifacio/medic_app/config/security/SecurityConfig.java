package com.bonifacio.medic_app.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.bonifacio.medic_app.config.filter.JwtTokenValidate;
import com.bonifacio.medic_app.utils.JwtUtils;

import static  org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->{
                    request
                            .requestMatchers(antMatcher(HttpMethod.GET,"/api/v1/patient/**/")).hasAuthority("READ")
                            .requestMatchers(antMatcher(HttpMethod.POST,"/api/v1/patient/**/")).hasAnyAuthority("ADMIN","NURSE","MEDIC","CREATE")
                            .requestMatchers(antMatcher(HttpMethod.PUT,"/api/v1/patient/**/")).hasAnyAuthority("ROLE_ADMIN","MEDIC","NURSE")
                            .requestMatchers(antMatcher(HttpMethod.DELETE,"/api/patient/**/")).hasAnyAuthority("ADMIN")
                            .requestMatchers(antMatcher("/swagger-ui/**/")).permitAll()
                            .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.POST,"/api/v1/auth/**/")).permitAll()
                            .requestMatchers(antMatcher(HttpMethod.GET,"/api/v1/familiar/**/")).hasAuthority("READ")
                            .requestMatchers(antMatcher(HttpMethod.POST,"/api/v1/familiar/**/")).hasAnyAuthority("ROLE_ADMIN","ROLE_NURSE","ROLE_MEDIC","CREATE")
                            .requestMatchers(antMatcher(HttpMethod.PUT,"/api/v1/familiar/**/")).hasAnyAuthority("ROLE_ADMIN","ROLE_MEDIC","ROLE_NURSE")
                            .anyRequest().permitAll();
                })
                .sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(new JwtTokenValidate(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }
    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("test")
                .password("1234")
                .roles()
                .build());
        return manager;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity,PasswordEncoder passwordEncoder)
    throws Exception{
        AuthenticationManagerBuilder build = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
                build.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder);
        return build.build();

    }
}
