package com.example.opeyemi.storytime.DataModels;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by opeyemi on 02/08/2015.
 */
public class Book extends RealmObject {
    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    private boolean favourite=false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Ignore
    public String author;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Ignore
    public String genre;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
