package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 20.10.2014.
 */
public class StudioPhoto implements Serializable {

    private int Id;
    private String ImageUrl;

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    StudioPhoto(Integer Id, String ImageUrl) {
        this.Id = Id;
        this.ImageUrl = ImageUrl;
    }
}
