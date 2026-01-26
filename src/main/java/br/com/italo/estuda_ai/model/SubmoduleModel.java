package br.com.italo.estuda_ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

@Table(name = "submodules")
@Entity
public class SubmoduleModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false ,unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "submodule", fetch = FetchType.LAZY)
    private Set<LinkModel> links;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "submodule", fetch = FetchType.LAZY)
    private Set<QuestionModel> questions;

    @Column(nullable = false)
    private Duration averageDuration;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private ModuleModel module;

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

    public Set<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(Set<LinkModel> links) {
        this.links = links;
    }

    public Set<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionModel> questions) {
        this.questions = questions;
    }

    public Duration getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(Duration averageDuration) {
        this.averageDuration = averageDuration;
    }

    public ModuleModel getModule() {
        return module;
    }

    public void setModule(ModuleModel module) {
        this.module = module;
    }
}
