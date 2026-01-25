package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.model.QuestionModel;
import br.com.italo.estuda_ai.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionModel> getAll(){
        return this.questionRepository.findAll();
    }
}
