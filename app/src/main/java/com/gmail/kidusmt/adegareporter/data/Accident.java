package com.gmail.kidusmt.adegareporter.data;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by KidusMT on 12/30/2017.
 */
@Entity
public class Accident {
    @Id
    public long id;
    public String location;
    public String date;
    public String plate;
    public String description;
    public String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
