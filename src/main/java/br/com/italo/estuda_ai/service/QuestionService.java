package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestQuestion;
import br.com.italo.estuda_ai.DTOs.responses.ResponseQuestion;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.QuestionModel;
import br.com.italo.estuda_ai.model.SubmoduleModel;
import br.com.italo.estuda_ai.repository.QuestionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<QuestionModel> getAllQuestions(){
        return this.questionRepository.findAll();
    }

    public QuestionModel getQuestionById(String id){
        return this.questionRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("Questão"));
    }

    public QuestionModel createQuestion(RequestQuestion request){
        QuestionModel newQuestion = new QuestionModel();

        newQuestion.setTitle(request.title());
        newQuestion.setLink(request.link());
        newQuestion.setType(request.type());

        SubmoduleModel submoduleRed = entityManager.getReference(SubmoduleModel.class, UUID.fromString(request.submodule_id()));

        newQuestion.setSubmodule(submoduleRed);

        return this.questionRepository.save(newQuestion);
    }

    public void deleteQuestion(String  id){
        UUID questionId = UUID.fromString(id);
        if(! this.questionRepository.existsById(questionId)) throw new ResourceNotFoundException("Questão");
        this.questionRepository.deleteById(questionId);
    }

    public QuestionModel updateQuestion(String id, RequestQuestion request) {

        UUID questionId = UUID.fromString(id);

        if(! this.questionRepository.existsById(questionId)) throw new ResourceNotFoundException("Questão");

        QuestionModel question = this.entityManager.getReference(QuestionModel.class, questionId);

        question.setTitle(request.title());
        question.setLink(request.link());
        question.setType(request.type());

        SubmoduleModel submoduleRef = entityManager.getReference(SubmoduleModel.class, UUID.fromString(request.submodule_id()));
        question.setSubmodule(submoduleRef);

        return this.questionRepository.save(question);
    }
}
