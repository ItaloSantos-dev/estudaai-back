package br.com.italo.estuda_ai.controller;


import br.com.italo.estuda_ai.DTOs.requests.RequestModule;
import br.com.italo.estuda_ai.DTOs.responses.ResponseModule;
import br.com.italo.estuda_ai.model.ModuleModel;
import br.com.italo.estuda_ai.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public List<ModuleModel> getAllModules(){
        return this.moduleService.getAllModules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModule> getModule(@PathVariable String id){
        ModuleModel module = this.moduleService.getModuleById(id);

        ResponseModule response = new ResponseModule(
                module.getId(), module.getName(), module.getAverageDuration(), module.getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseModule> createModule(@RequestBody RequestModule requestCreateModule){
        ModuleModel newModule = moduleService.createModule(requestCreateModule);

        ResponseModule response = new ResponseModule(
                newModule.getId(),
                newModule.getName(), newModule.getAverageDuration(), newModule.getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable String id){
        this.moduleService.deleteModule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModule> editModule(@PathVariable String id, @RequestBody RequestModule requestUpdateModule){
        ModuleModel module = this.moduleService.updateModule(id, requestUpdateModule);

        ResponseModule response = new ResponseModule(
                module.getId(), module.getName(), module.getAverageDuration(), module.getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
