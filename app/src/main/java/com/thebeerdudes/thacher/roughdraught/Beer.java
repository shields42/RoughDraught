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
        this.name = "Missing";
        this.brewery = "Missing";
        this.style = "Missing";
        this.abv = 0;
        this.ibu = 0;
        this.rating = 0;
        this.description = "Missing";

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
        if(name.equals(null) || name==null || name=="" || name.toString().equals(null)){
            return "ERROR";
        }
        else{
            return name.toString();
        }
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

    @Override
    public String toString() {
        String returnVal = this.getName() + "\n";
        return this.getName();
    }
}