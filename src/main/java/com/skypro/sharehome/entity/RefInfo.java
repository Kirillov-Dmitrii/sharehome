package com.skypro.sharehome.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Класс для описания справочной информации
 */
@Entity
public class RefInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String documentName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "share_home_id")
    private ShareHome shareHome;

    public RefInfo() {
    }

    public RefInfo(String documentName, String description) {
        this(documentName, description, null);
    }

    public RefInfo(String documentName, String description, ShareHome shareHome) {
        this(null, documentName, description, shareHome);
    }

    public RefInfo(Long id, String documentName, String description,ShareHome shareHome) {
        this.id = id;
        this.documentName = documentName;
        this.description = description;
        this.shareHome = shareHome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        RefInfo refInfo = (RefInfo) o;
        return Objects.equals(id, refInfo.id) && Objects.equals(documentName, refInfo.documentName) && Objects.equals(description, refInfo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentName, description);
    }

    @Override
    public String toString() {
        return "RefInfo{" +
                "id=" + id +
                ", documentName='" + documentName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
