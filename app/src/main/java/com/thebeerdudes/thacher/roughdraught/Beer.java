package com.thebeerdudes.thacher.roughdraught;

import java.io.Serializable;

/**
 * Created by thach on 10/24/2017.
 */


public class Beer implements Comparable<Beer>, Serializable{
    protected String name;
    protected String brewery;
    protected String style;
    protected double abv;
    protected int ibu;
    protected long rating;
    protected String description;
    protected int _id;

    @Override
    public int compareTo(Beer b) {
        if (this.getRating() > b.getRating()) {
            return -1;
        } else if (this.getRating() == b.getRating()) {
            return 0;
        } else
            return 1;
    }

    public Beer() {

    }

    public Beer(String name, String brewery, String style, double abv, int ibu, long rating, String description) {
        this.name = name;
        this.brewery = brewery;
        this.style = style;
        this.abv = abv;
        this.ibu = ibu;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}