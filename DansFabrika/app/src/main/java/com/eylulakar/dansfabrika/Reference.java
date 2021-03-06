package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 20.10.2014.
 */
public class Reference implements Serializable {

    private int Id;
    private String Title;
    private String ImageUrl;

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    Reference(Integer id, String title, String imageUrl) {
       this.Id = id;
       this.Title = title;
       this.ImageUrl = imageUrl;
    }
}
