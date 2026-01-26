package br.com.italo.estuda_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Table(name = "modules")
@Entity
public class ModuleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false ,unique = true)
    private String name;

    @Column(nullable = false)
    private Duration averageDuration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "module")
    private Set<SubmoduleModel> submodules = new HashSet<>();

    public ModuleModel(){

    }
    public ModuleModel(String name, Duration averageDuration) {
        this.name = name;
        this.averageDuration = averageDuration;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(Duration averageDuration) {
        this.averageDuration = averageDuration;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public Set<SubmoduleModel> getSubmodules() {
        return submodules;
    }

    public void setSubmodules(Set<SubmoduleModel> submodules) {
        this.submodules = submodules;
    }
}
