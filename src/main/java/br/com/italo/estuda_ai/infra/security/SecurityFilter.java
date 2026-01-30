package br.com.italo.estuda_ai.infra.security;

import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.repository.UserRepository;
import br.com.italo.estuda_ai.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = separateToken(request);
        if(token!= null){
            String email = this.tokenService.validateToken(token);
            UserDetails user = this.userRepository.findByEmail(email);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(request, response);
    }

    private String  separateToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader==null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
