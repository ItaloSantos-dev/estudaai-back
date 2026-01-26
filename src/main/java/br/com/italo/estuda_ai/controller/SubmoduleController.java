package br.com.italo.estuda_ai.controller;

import br.com.italo.estuda_ai.DTOs.requests.RequestSubmodule;
import br.com.italo.estuda_ai.DTOs.responses.ResponseSubmodule;
import br.com.italo.estuda_ai.model.SubmoduleModel;
import br.com.italo.estuda_ai.service.SubmoduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submodules")
public class SubmoduleController {
    @Autowired
    private SubmoduleService subModuleService;

    @GetMapping
    public List<SubmoduleModel> getAllSubmodules(){
        return this.subModuleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSubmodule> getModule(@PathVariable String id){
        SubmoduleModel submodule = this.subModuleService.getSubmoduleById(id);

        ResponseSubmodule response = new ResponseSubmodule(
                submodule.getId(), submodule.getName(), submodule.getAverageDuration(), submodule.getModule().getName(), submodule.getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseSubmodule> createSubmodule(@RequestBody RequestSubmodule requestCreateSubmodule){
        SubmoduleModel submodule = this.subModuleService.createSubmdule(requestCreateSubmodule);

        ResponseSubmodule response = new ResponseSubmodule(
                submodule.getId(), submodule.getName(), submodule.getAverageDuration(), submodule.getModule().getName(), submodule.getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmodule(@PathVariable String id){
        this.subModuleService.deleteSubmodule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSubmodule> updateSubmodule(
            @PathVariable String id, @RequestBody RequestSubmodule requestUpdateSubmodule
    ){
        SubmoduleModel submodule = this.subModuleService.updateSubmodule(id, requestUpdateSubmodule);

        ResponseSubmodule response = new ResponseSubmodule(
                submodule.getId(), submodule.getName(), submodule.getAverageDuration(), submodule.getModule().getName(), submodule.getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
