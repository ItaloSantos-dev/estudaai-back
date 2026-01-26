package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestSubmodule;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.LinkModel;
import br.com.italo.estuda_ai.model.ModuleModel;
import br.com.italo.estuda_ai.model.QuestionModel;
import br.com.italo.estuda_ai.model.SubmoduleModel;
import br.com.italo.estuda_ai.repository.ModuleRepository;
import br.com.italo.estuda_ai.repository.SubmoduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class SubmoduleService {
    @Autowired
    private SubmoduleRepository submoduleRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public List<SubmoduleModel> getAll(){
        return  this.submoduleRepository.findAll();
    }

    public SubmoduleModel getSubmoduleById(String id){
        return this.submoduleRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("Submodulo"));
    }

    public SubmoduleModel createSubmdule(RequestSubmodule request){
        SubmoduleModel newSubmodule = new SubmoduleModel();

        newSubmodule.setName(request.name());
        newSubmodule.setDescription(request.description());
        newSubmodule.setAverageDuration(Duration.ofHours(request.averageDuration()));

        ModuleModel moduleRef = entityManager.getReference(ModuleModel.class, UUID.fromString(request.module_id()));
        newSubmodule.setModule(moduleRef);

        return this.submoduleRepository.save(newSubmodule);

    }

    public void deleteSubmodule(String id){
        UUID submoduleId = UUID.fromString(id);
        if(! this.submoduleRepository.existsById(submoduleId)) throw new ResourceNotFoundException("Submodulo");
        this.submoduleRepository.deleteById(submoduleId);
    }


    public SubmoduleModel updateSubmodule(String id, RequestSubmodule request){

        UUID submoduleId = UUID.fromString(id);
        if(! this.submoduleRepository.existsById(submoduleId)) throw new ResourceNotFoundException("Submodulo");

        SubmoduleModel submodule = this.entityManager.getReference(SubmoduleModel.class, submoduleId);

        submodule.setName(request.name());
        submodule.setDescription(request.description());
        submodule.setAverageDuration(Duration.ofHours(request.averageDuration()));

        if(! submodule.getModule().getId().toString().equals(request.module_id())){
            ModuleModel moduleRef = entityManager.getReference(ModuleModel.class, UUID.fromString(request.module_id()));
            submodule.setModule(moduleRef);
        }

        return this.submoduleRepository.save(submodule);
    }

    public Set<LinkModel> getLinksOfSubmodule(String id){

        UUID submoduleId = UUID.fromString(id);
        if(! this.submoduleRepository.existsById(submoduleId)) throw new ResourceNotFoundException("Submodulo");
        SubmoduleModel submoduleRef = entityManager.getReference(SubmoduleModel.class, submoduleId);
        return submoduleRef.getLinks();
    }

    public Set<QuestionModel> getQuestionsOfSubmodule(String id){
        UUID submoduleId = UUID.fromString(id);
        if(! this.submoduleRepository.existsById(submoduleId)) throw new ResourceNotFoundException("Submodulo");

        SubmoduleModel submoduleRef = entityManager.getReference(SubmoduleModel.class, submoduleId);
        return submoduleRef.getQuestions();
    }
}
