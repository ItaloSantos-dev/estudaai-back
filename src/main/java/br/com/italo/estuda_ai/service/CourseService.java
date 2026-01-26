package br.com.italo.estuda_ai.service;


import br.com.italo.estuda_ai.DTOs.requests.RequestCourse;
import br.com.italo.estuda_ai.exceptions.ResourceNotFoundException;
import br.com.italo.estuda_ai.model.CourseModel;
import br.com.italo.estuda_ai.model.ModuleModel;
import br.com.italo.estuda_ai.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<CourseModel> getAllCourses(){
        return this.courseRepository.findAll();
    }

    public CourseModel getCourseById(String id){
        CourseModel course =  this.courseRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("Curso"));
        return course;
    }


    public CourseModel createCourse(RequestCourse request){
        CourseModel newCourse = new CourseModel();

        newCourse.setName(request.name());
        newCourse.setDescription(request.description());
        newCourse.setAverageDuration(Duration.ofHours(request.averageDuration()));
        return newCourse;
    }

    public void deleteCourse(String  id){
        UUID courseId = UUID.fromString(id);

        if(! this.courseRepository.existsById(courseId))throw new ResourceNotFoundException("Curso");

        this.courseRepository.deleteById(courseId);
    }

    public CourseModel updateCourse(String id, RequestCourse request){

        UUID courseId = UUID.fromString(id);
        if(! this.courseRepository.existsById(courseId))throw new ResourceNotFoundException("Curso");

        CourseModel courseRef = this.entityManager.getReference(CourseModel.class, courseId);

        courseRef.setName(request.name());
        courseRef.setDescription(request.description());
        courseRef.setAverageDuration(Duration.ofHours(request.averageDuration()));


        return this.courseRepository.save(courseRef);
    }


    public Set<ModuleModel> getModulesOfCourse(String id){
        UUID courseId = UUID.fromString(id);
        if(!this.courseRepository.existsById(courseId))throw new ResourceNotFoundException("Curso");
        CourseModel courseRef = entityManager.getReference(CourseModel.class, courseId);
        return courseRef.getModules();

    }

}
