package com.nmw.example.springbatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private  Integer year;
    private Integer movieLength;
    private String title;
    private String subject;
    private String actor;
    private String actress;
    private  String director;
    private Integer popularity;
    private Boolean award;
    private  String images;

    public Film() {
    }

    public Film(Integer id, Integer year, Integer movieLength, String title, String subject, String actor, String actress, String director, Integer popularity, Boolean award, String images) {
        this.id = id;
        this.year = year;
        this.movieLength = movieLength;
        this.title = title;
        this.subject = subject;
        this.actor = actor;
        this.actress = actress;
        this.director = director;
        this.popularity = popularity;
        this.award = award;
        this.images = images;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Boolean getAward() {
        return award;
    }

    public void setAward(Boolean award) {
        this.award = award;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", year=" + year +
                ", movieLength=" + movieLength +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", actor='" + actor + '\'' +
                ", actress='" + actress + '\'' +
                ", director='" + director + '\'' +
                ", popularity=" + popularity +
                ", award=" + award +
                ", images='" + images + '\'' +
                '}';
    }
}
