package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestLogin;
import br.com.italo.estuda_ai.DTOs.requests.RequestRegister;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public Authentication login(RequestLogin request){
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        return this.authenticationManager.authenticate(usernamePassword);
    }

    public UserModel register(RequestRegister request){
        if(this.userRepository.existsByEmail(request.email())) throw new ResourceNotFoundException();

        String passwordEncode = new BCryptPasswordEncoder().encode(request.password());

        UserModel newUser = new UserModel(request.name(), request.email(), passwordEncode, request.role(), request.nasciment());
        return this.userRepository.save(newUser);
    }
}
