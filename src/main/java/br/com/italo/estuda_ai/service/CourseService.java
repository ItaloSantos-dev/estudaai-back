package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.model.CourseModel;
import br.com.italo.estuda_ai.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public List<CourseModel> getAll(){
        return this.courseRepository.findAll();
    }

}
