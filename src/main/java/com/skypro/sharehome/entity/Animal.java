package com.skypro.sharehome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Animal {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToOne
    private Client owner;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private Collection<Report> reports;

    @ManyToOne
    @JoinColumn(name = "share_home_id")
    private ShareHome shareHome;

    public Animal() {
    }

    public Animal(String name, Client owner) {
        this(name, owner, null);
    }

    public Animal(String name, Client owner, ShareHome shareHome) {
        this(null, name, owner, shareHome);
    }

    public Animal(Integer id, String name, Client owner, ShareHome shareHome) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.shareHome = shareHome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public ShareHome getShareHome() {
        return shareHome;
    }

    public void setShareHome(ShareHome shareHome) {
        this.shareHome = shareHome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(name, animal.name) && Objects.equals(owner, animal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
