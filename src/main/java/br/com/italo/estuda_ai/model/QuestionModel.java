package br.com.italo.estuda_ai.model;

import br.com.italo.estuda_ai.model.enums.QuestionType;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;
@Table(name = "questions")
@Entity
public class QuestionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    QuestionType type;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "submodule_id")
    private SubmoduleModel submodule;


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SubmoduleModel getSubmodule() {
        return submodule;
    }

    public void setSubmodule(SubmoduleModel submodule) {
        this.submodule = submodule;
    }
}
