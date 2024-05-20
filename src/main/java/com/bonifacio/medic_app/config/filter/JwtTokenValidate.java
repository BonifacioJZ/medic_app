package com.bonifacio.medic_app.config.filter;

import java.io.IOException;
import java.util.Collection;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bonifacio.medic_app.utils.JwtUtils;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtTokenValidate extends OncePerRequestFilter {
    
    private final JwtUtils jwtUtils;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String token = request.getHeader(HttpHeaders.AUTHORIZATION);
                if(StringUtils.isNotEmpty(token)){
                    token = token.substring(7);

                    DecodedJWT decodedJWT = jwtUtils.validateJWT(token);
                    
                    String username = jwtUtils.getUsername(decodedJWT);
                    String stringClaims = jwtUtils.getClaim(decodedJWT,"authorities").asString();

                    Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringClaims);
                    
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username,null, authorities);
                    securityContext.setAuthentication(authenticationToken);
                    SecurityContextHolder.setContext(securityContext);
                }
                filterChain.doFilter(request, response);
    }
}