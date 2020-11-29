package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 20.10.2014.
 */
public class Video implements Serializable {

    private int Id;
    private String Title;
    private String Url;
    private String ImageUrl;
    private String Other;

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

    public String getUrl() {
        return Url;
    }
    public void setUrl(String Url) {
        this.Other = Url;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getOther() {
        return Other;
    }
    public void setOther(String Other) {
        this.Other = Other;
    }

    Video(Integer Id, String Title, String Other, String Url, String ImageUrl) {
        this.Id = Id;
        this.Title = Title;
        this.Other = Other;
        this.Url = Url;
        this.ImageUrl = ImageUrl;
    }
}
