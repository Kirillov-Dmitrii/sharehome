package com.skypro.sharehome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Основной класс, содержащий информацию о приюте
 */
@Entity
public class ShareHome {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private TypeAnimal typeAnimal;

    @OneToMany(mappedBy = "shareHome")
    @JsonIgnore
    private Collection<Shedule> shedules;

    private String address;

    @OneToMany(mappedBy = "shareHome")
    private Collection<RefInfo> refInfo;

    @OneToMany(mappedBy = "shareHome")
    @JsonIgnore
    private Collection<Animal> animals;

    @OneToMany(mappedBy = "shareHome")
    @JsonIgnore
    private Collection<Client> clients;

    public ShareHome(Long id, String name, TypeAnimal typeAnimal, String address) {
        this.id = id;
        this.name = name;
        this.typeAnimal = typeAnimal;
        this.address = address;
    }

    public ShareHome() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeAnimal getTypeAnimal() {
        return typeAnimal;
    }

    public void setTypeAnimal(TypeAnimal typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    public Collection<Shedule> getShedules() {
        return shedules;
    }

    public void setShedules(Collection<Shedule> shedules) {
        this.shedules = shedules;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<RefInfo> getRefInfo() {
        return refInfo;
    }

    public void setRefInfo(Collection<RefInfo> refInfo) {
        this.refInfo = refInfo;
    }

    public Collection<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Collection<Animal> animals) {
        this.animals = animals;
    }

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShareHome shareHome = (ShareHome) o;
        return Objects.equals(id, shareHome.id) && Objects.equals(name, shareHome.name) && Objects.equals(address, shareHome.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "ShareHome{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
