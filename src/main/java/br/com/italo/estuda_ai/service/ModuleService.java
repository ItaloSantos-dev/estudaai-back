package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestModule;
import br.com.italo.estuda_ai.DTOs.responses.ResponseSubmodule;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.CourseModel;
import br.com.italo.estuda_ai.model.ModuleModel;
import br.com.italo.estuda_ai.model.SubmoduleModel;
import br.com.italo.estuda_ai.repository.CourseRepository;
import br.com.italo.estuda_ai.repository.ModuleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;



    @PersistenceContext
    private EntityManager entityManager;

    public List<ModuleModel> getAllModules(){
        return this.moduleRepository.findAll();
    }

    public ModuleModel getModuleById(String id){
        return this.moduleRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("Modulo"));
    }

    public ModuleModel createModule(RequestModule request){
        ModuleModel newModule = new ModuleModel();
        newModule.setName(request.name());
        newModule.setAverageDuration(Duration.ofHours(request.averageDuration()));

        CourseModel courseRef = entityManager.getReference(CourseModel.class, UUID.fromString(request.course_id()));

        newModule.setCourse(courseRef);
        return this.moduleRepository.save(newModule);
    }

    public void deleteModule(String id){
        UUID moduleId = UUID.fromString(id);

        if(! this.moduleRepository.existsById(moduleId))throw new ResourceNotFoundException("Modulo");

        this.moduleRepository.deleteById(moduleId);
    }

    public ModuleModel updateModule(String id, RequestModule request){

        if(!this.moduleRepository.existsById(UUID.fromString(id)))throw new ResourceNotFoundException("Modulo");

        ModuleModel module = this.entityManager.getReference(ModuleModel.class, UUID.fromString(id));

        module.setName(request.name());
        module.setAverageDuration(Duration.ofHours(request.averageDuration()));

        if( ! module.getCourse().getId().toString().equals(request.course_id())){

            CourseModel courseRef = entityManager.getReference(CourseModel.class, UUID.fromString(request.course_id()));
            module.setCourse(courseRef);
        }

        return this.moduleRepository.save(module);
    }

    public Set<SubmoduleModel> getSubmodulesOfModule(String id){
        UUID moduleId = UUID.fromString(id);
        if(! this.moduleRepository.existsById(moduleId))throw new ResourceNotFoundException("Modulo");

        ModuleModel moduleRef = this.entityManager.getReference(ModuleModel.class, moduleId);

        return moduleRef.getSubmodules();
    }



}
