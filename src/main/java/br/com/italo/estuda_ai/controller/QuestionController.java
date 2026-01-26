package br.com.italo.estuda_ai.controller;

import br.com.italo.estuda_ai.DTOs.requests.RequestQuestion;
import br.com.italo.estuda_ai.DTOs.responses.ResponseQuestion;
import br.com.italo.estuda_ai.model.QuestionModel;
import br.com.italo.estuda_ai.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<QuestionModel> getQuestions(){
        return this.questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseQuestion> getLink(@PathVariable String id){
        QuestionModel question = this.questionService.getQuestionById(id);

        ResponseQuestion response = new ResponseQuestion(
                question.getId(), question.getTitle(), question.getLink(), question.getSubmodule().getName(), question.getSubmodule().getModule().getName(), question.getSubmodule().getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseQuestion> createQuestion(@RequestBody RequestQuestion requestQuestion){
        QuestionModel question = this.questionService.createQuestion(requestQuestion);

        ResponseQuestion response = new ResponseQuestion(
                question.getId(), question.getTitle(), question.getLink(), question.getSubmodule().getName(), question.getSubmodule().getModule().getName(), question.getSubmodule().getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id){
        this.questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseQuestion> updateQuestion(@PathVariable String id, @RequestBody RequestQuestion requestQuestion){
        QuestionModel question = this.questionService.updateQuestion(id, requestQuestion);


        ResponseQuestion response = new ResponseQuestion(
                question.getId(), question.getTitle(), question.getLink(), question.getSubmodule().getName(), question.getSubmodule().getModule().getName(), question.getSubmodule().getModule().getCourse().getName()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
