package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 20.10.2014.
 */
public class News implements Serializable {

    private int Id;
    private String Title;
    private String Description;
    private String DescriptionShort;
    private String ImageThumbUrl;
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

    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDescriptionShort() {
        return DescriptionShort;
    }
    public void setDescriptionShort(String DescriptionShort) {
        this.DescriptionShort = DescriptionShort;
    }
    public String getImageThumbUrl() {
        return ImageThumbUrl;
    }
    public void setImageThumbUrl(String ImageThumbUrl) {
        this.ImageThumbUrl = ImageThumbUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    News(Integer id, String title, String description, String descriptionShort, String imageUrl, String imageThumbUrl) {
        this.Id = id;
        this.Title = title;
        this.Description = description;
        this.DescriptionShort = descriptionShort;
        this.ImageUrl = imageUrl;
        this.ImageThumbUrl = imageThumbUrl;
    }
}
