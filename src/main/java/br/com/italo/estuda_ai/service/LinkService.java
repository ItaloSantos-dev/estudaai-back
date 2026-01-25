package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.model.LinkModel;
import br.com.italo.estuda_ai.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public List<LinkModel> getAll(){
        return this.linkRepository.findAll();
    }
}
