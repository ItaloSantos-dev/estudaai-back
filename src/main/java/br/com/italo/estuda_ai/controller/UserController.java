package br.com.italo.estuda_ai.controller;

import br.com.italo.estuda_ai.DTOs.requests.RequestSignUpInCourse;
import br.com.italo.estuda_ai.DTOs.responses.ResponseUser;
import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ResponseUser>> getAllUsers(){

        List<UserModel> users = this.userService.getAll();
        List<ResponseUser> response = users.stream().map(user -> new ResponseUser(user.getName(), user.getEmail(), user.getRole(), user.getNasciment())).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup/{id}")
    public ResponseEntity signUpInCourse(@PathVariable String id, @RequestBody RequestSignUpInCourse requestSignUpInCourse
    ){
        this.userService.signUpInCourse(id, requestSignUpInCourse);
        return ResponseEntity.ok().build();
    }
}
