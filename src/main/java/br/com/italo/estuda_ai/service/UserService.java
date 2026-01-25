package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAll(){
        return this.userRepository.findAll();
    }
}
