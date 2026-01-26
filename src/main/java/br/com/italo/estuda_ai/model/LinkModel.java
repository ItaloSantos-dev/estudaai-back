package br.com.italo.estuda_ai.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;
@Table(name = "links")
@Entity
public class LinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "submodule_id")
    private SubmoduleModel submodule;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
