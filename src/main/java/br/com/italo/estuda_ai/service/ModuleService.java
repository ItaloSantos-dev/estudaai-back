package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.model.ModuleModel;
import br.com.italo.estuda_ai.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public List<ModuleModel> getAll(){
        return this.moduleRepository.findAll();
    }
}
