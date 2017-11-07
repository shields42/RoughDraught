package com.thebeerdudes.thacher.roughdraught;

/**
 * Created by thach on 10/24/2017.
 */

public class Beer {
    private int _id;
    protected String name;
    protected String type;
    protected String style;
    protected String brewery;
    protected String imagePath;
    protected int    stars;
    protected long   rating;
    protected String description;
    protected String location;
    protected String _date;

    public Beer(String name, String type, String style, String brewery, String imagePath, int stars, long rating, String description, String location, String date) {
        this.name = name;
        this.type = type;
        this.style = style;
        this.brewery = brewery;
        this.imagePath = imagePath;
        this.stars = stars;
        this.rating = rating;
        this.description = description;
        this.location = location;
        this._date = date;
    }
    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_date() {
        return _date;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
