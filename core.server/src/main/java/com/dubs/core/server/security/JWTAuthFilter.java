package com.dubs.core.server.security;


import com.dubs.core.server.entity.Credentials;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsServiceImpl userDetailsSvc;

    @Autowired
    JWTService jwtSvc;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Optional<String> token = jwtSvc.extractToken(request);
        if(token.isEmpty()){
            log.warn("Request does not contain JWT Bearer Token");
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = token.get();
        jwtSvc.checkJwtToken(jwtToken);
        String username = jwtSvc.extractUsername(jwtToken);

        //Validate Token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Credentials userDetails = (Credentials) userDetailsSvc.loadUserByUsername(username);
            if(null != userDetails){
                //TODO: FIX AUTHORITIES
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,null );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("User Authenticated - Username: {} | UserID: {}",userDetails.getUsername(),userDetails.getUserId());
            } else {

            }
        }

        filterChain.doFilter(request, response);
    }

}
