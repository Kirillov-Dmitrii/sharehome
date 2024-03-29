package com.skypro.sharehome.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Report {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    private String diet;
    private String health;
    private String actions;
    private LocalDate dayReport;

    public Report() {
    }

    public Report(Integer id, Animal animal, Avatar avatar, String diet, String health, String actions,
                  LocalDate dayReport) {
        this.id = id;
        this.animal = animal;
        this.diet = diet;
        this.health = health;
        this.actions = actions;
        this.dayReport = dayReport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public LocalDate getDayReport() {
        return dayReport;
    }

    public void setDayReport(LocalDate dayReport) {
        this.dayReport = dayReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(diet, report.diet) && Objects.equals(health, report.health) && Objects.equals(actions, report.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diet, health, actions);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", diet='" + diet + '\'' +
                ", health='" + health + '\'' +
                ", actions='" + actions + '\'' +
                '}';
    }
}
