package br.com.italo.estuda_ai.controller;

import br.com.italo.estuda_ai.DTOs.requests.RequestLink;
import br.com.italo.estuda_ai.DTOs.responses.ResponseLink;
import br.com.italo.estuda_ai.model.LinkModel;
import br.com.italo.estuda_ai.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping
    public List<LinkModel> getAllLinks(){
        return this.linkService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLink> getLink(@PathVariable String id){
        LinkModel link = this.linkService.getLinkById(id);
        ResponseLink response = new ResponseLink(
                link.getId(), link.getTitle(), link.getLink(), link.getSubmodule().getName(),
                link.getSubmodule().getModule().getName(), link.getSubmodule().getModule().getCourse().getName()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseLink> createLink(@RequestBody RequestLink requestLink){
        LinkModel link = this.linkService.createLink(requestLink);

        ResponseLink response = new ResponseLink(
                link.getId(), link.getTitle(), link.getLink(), link.getSubmodule().getName(),
                link.getSubmodule().getModule().getName(), link.getSubmodule().getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable String id){
        this.linkService.deleteLink(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseLink> updateLink(@PathVariable String id, @RequestBody RequestLink requestLink){
        LinkModel link = this.linkService.updateLink(id, requestLink);

        ResponseLink response = new ResponseLink(
                link.getId(), link.getTitle(), link.getLink(), link.getSubmodule().getName(),
                link.getSubmodule().getModule().getName(), link.getSubmodule().getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
