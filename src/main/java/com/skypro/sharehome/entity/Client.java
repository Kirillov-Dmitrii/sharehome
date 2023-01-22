package com.skypro.sharehome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Класс, предназначенный для хранения данных о клиентах
 */
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String phone;
    private Long idChat;
    private Boolean owner;

    @ManyToOne
    @JoinColumn(name = "share_home_id")
    private ShareHome shareHome;

    private Integer countDays;

    public Client() {
    }

    public Client(Integer id, String name, String phone, Long idChat, Boolean owner, Animal animal, ShareHome shareHome,
                  Integer countDays) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.idChat = idChat;
        this.owner = owner;
        this.shareHome = shareHome;
        this.countDays = countDays;
    }

    public Client(String name, String phone, Long idChat, ShareHome shareHome) {
        this(null, name, phone, idChat, null, null, shareHome, null);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public ShareHome getShareHome() {
        return shareHome;
    }

    public void setShareHome(ShareHome shareHome) {
        this.shareHome = shareHome;
    }

    public Integer getCountDays() {
        return countDays;
    }

    public void setCountDays(Integer countDays) {
        this.countDays = countDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(phone, client.phone) && Objects.equals(idChat, client.idChat) && Objects.equals(owner, client.owner) && Objects.equals(countDays, client.countDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, idChat, owner, countDays);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", owner=" + owner +
                ", countDays=" + countDays +
                '}';
    }
}
