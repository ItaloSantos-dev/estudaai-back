package br.com.italo.estuda_ai.service;

import br.com.italo.estuda_ai.DTOs.requests.RequestSignUpInCourse;
import br.com.italo.estuda_ai.exceptions.UserAlreadySignInCourseException;
import br.com.italo.estuda_ai.model.CourseModel;
import br.com.italo.estuda_ai.model.UserModel;
import br.com.italo.estuda_ai.repository.CourseRepository;
import br.com.italo.estuda_ai.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public List<UserModel> getAll(){
        return this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public void signUpInCourse(String id, RequestSignUpInCourse request){

        UserModel user  = (UserModel)this.userRepository.findByEmail(request.email());

        user.getCourses().forEach( course ->{
            if (course.getId().equals(UUID.fromString(id))) throw  new UserAlreadySignInCourseException(course.getName(), user.getEmail());
        });

        user.getCourses().add(this.entityManager.getReference(CourseModel.class, UUID.fromString(id)));

        this.userRepository.save(user);

    }

    public List<CourseModel> getAllCoursesOfUser(String id){
        return new ArrayList<>(this.entityManager.getReference(UserModel.class, UUID.fromString(id)).getCourses());

    }
}
