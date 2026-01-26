package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestModule;
import br.com.italo.estuda_ai.model.CourseModel;
import br.com.italo.estuda_ai.model.ModuleModel;
import br.com.italo.estuda_ai.repository.CourseRepository;
import br.com.italo.estuda_ai.repository.ModuleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
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
        return this.moduleRepository.findById(UUID.fromString(id)).get();
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
        this.moduleRepository.deleteById(UUID.fromString(id));
    }

    public ModuleModel updateModule(String id, RequestModule request){
        ModuleModel module = this.entityManager.getReference(ModuleModel.class, UUID.fromString(id));

        module.setName(request.name());
        module.setAverageDuration(Duration.ofHours(request.averageDuration()));

        if( ! module.getCourse().getId().toString().equals(request.course_id())){

            CourseModel courseRef = entityManager.getReference(CourseModel.class, UUID.fromString(request.course_id()));
            module.setCourse(courseRef);
        }

        return this.moduleRepository.save(module);
    }



}
