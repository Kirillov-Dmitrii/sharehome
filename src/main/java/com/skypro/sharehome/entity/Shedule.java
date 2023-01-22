package com.skypro.sharehome.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
/**
 * Расписание работы
 */
public class Shedule {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "share_home_id")
    private ShareHome shareHome;

    private String dayOfWeek;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    public Shedule() {
    }

    public Shedule(String dayOfWeek, LocalTime timeBegin, LocalTime timeEnd) {
        this(dayOfWeek, timeBegin, timeEnd, null);
    }

    public Shedule(String dayOfWeek, LocalTime timeBegin, LocalTime timeEnd, ShareHome shareHome) {
        this(null, dayOfWeek, timeBegin, timeEnd, shareHome);
    }

    public Shedule(Integer id, String dayOfWeek, LocalTime timeBegin, LocalTime timeEnd, ShareHome shareHome) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.shareHome = shareHome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
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
        Shedule shedule = (Shedule) o;
        return Objects.equals(id, shedule.id) && Objects.equals(dayOfWeek, shedule.dayOfWeek) && Objects.equals(timeBegin, shedule.timeBegin) && Objects.equals(timeEnd, shedule.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, timeBegin, timeEnd);
    }

    @Override
    public String toString() {
        return "Shedule{" +
                "id=" + id +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", timeBegin=" + timeBegin +
                ", timeEnd=" + timeEnd +
                '}';
    }

}
