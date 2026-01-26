package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestLink;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.LinkModel;
import br.com.italo.estuda_ai.model.SubmoduleModel;
import br.com.italo.estuda_ai.repository.LinkRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<LinkModel> getAll(){
        return this.linkRepository.findAll();
    }

    public  LinkModel getLinkById(String id){
        return this.linkRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("Link"));
    }

    public LinkModel createLink(RequestLink request){
        LinkModel newLink = new LinkModel();

        newLink.setTitle(request.title());
        newLink.setLink(request.link());

        SubmoduleModel submoduleRef = entityManager.getReference(SubmoduleModel.class, UUID.fromString(request.submodule_id()));

        newLink.setSubmodule(submoduleRef);
        return this.linkRepository.save(newLink);
    }

    public void deleteLink(String id){
        UUID linkId = UUID.fromString(id);

        if(! this.linkRepository.existsById(linkId))throw new EntityNotFoundException("Link");

        this.linkRepository.deleteById(linkId);
    }

    public LinkModel updateLink(String id, RequestLink request){

        UUID linkId = UUID.fromString(id);

        if(! this.linkRepository.existsById(linkId))throw new EntityNotFoundException("Link");

        LinkModel link = this.entityManager.getReference(LinkModel.class,linkId);

        link.setTitle(request.title());
        link.setLink(request.link());
        if (! link.getSubmodule().getId().toString().equals(request.submodule_id())){
            SubmoduleModel submoduleRef = entityManager.getReference(SubmoduleModel.class, UUID.fromString(request.submodule_id()));
            link.setSubmodule(submoduleRef);
        }
        return this.linkRepository.save(link);
    }


}
