package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 15.10.2014.
 */
public class Instructor implements Serializable {

    private String FirstName;
    private String LastName;
    private String Description;
    private String Branch;
    private String Gender;
    private Integer ImageId;
    private String ImageUrl;

    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }
    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        this.Description = description;
    }

    public String getBranch() {
        return Branch;
    }
    public void setBranch(String branch) {
        this.Branch = branch;
    }

    public Integer getImage() {
        return ImageId;
    }
    public void setImage(Integer imageId) {
        this.ImageId = imageId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

}
