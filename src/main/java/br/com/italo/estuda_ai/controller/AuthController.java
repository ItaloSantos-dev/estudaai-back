package br.com.italo.estuda_ai.controller;

import br.com.italo.estuda_ai.DTOs.requests.RequestLogin;
import br.com.italo.estuda_ai.DTOs.requests.RequestRegister;
import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody RequestLogin requestLogin){
        Authentication auth = this.authService.login(requestLogin);
        if(auth.isAuthenticated()) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RequestRegister requestRegister){
        UserModel newUser = this.authService.register(requestRegister);
        return ResponseEntity.ok().build();
    }
}
