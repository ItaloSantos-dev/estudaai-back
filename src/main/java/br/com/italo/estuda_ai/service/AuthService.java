package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestLogin;
import br.com.italo.estuda_ai.DTOs.requests.RequestRegister;
import br.com.italo.estuda_ai.exceptions.FailedLoginException;
import br.com.italo.estuda_ai.exceptions.ItemAlreadyExistsException;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.model.enums.UserRole;
import br.com.italo.estuda_ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
        UserModel newUser;
        try{
            String passwordEncode = new BCryptPasswordEncoder().encode(request.password());

            newUser = this.userRepository.save(new UserModel(request.name(), request.email(), passwordEncode, UserRole.USER, request.nasciment()));


        }catch (DataIntegrityViolationException exception){
            throw new ItemAlreadyExistsException("O email " + request.email() + " já esta cadastrado");
        }
        return newUser;
    }
}
