package br.com.italo.estuda_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "courses")
@Entity
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Duration averageDuration;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<UserModel> users;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<ModuleModel> modules = new HashSet<>();

    public CourseModel(){

    }

    public CourseModel(UUID id, String name, String description, Duration averageDuration, Set<UserModel> users, Set<ModuleModel> modules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.averageDuration = averageDuration;
        this.users = users;
        this.modules = modules;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(Duration averageDuration) {
        this.averageDuration = averageDuration;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    public Set<ModuleModel> getModules() {
        return modules;
    }

    public void setModules(Set<ModuleModel> modules) {
        this.modules = modules;
    }
}
