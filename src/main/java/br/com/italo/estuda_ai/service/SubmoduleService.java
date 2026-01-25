package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.model.SubmoduleModel;
import br.com.italo.estuda_ai.repository.SubmoduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmoduleService {
    @Autowired
    private SubmoduleRepository submoduleRepository;

    public List<SubmoduleModel> getAll(){
        return  this.submoduleRepository.findAll();
    }
}
