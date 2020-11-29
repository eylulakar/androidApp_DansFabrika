package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 20.10.2014.
 */
public class Program implements Serializable {

    private int Id;
    private String DayText;
    private String ContentText;

    public int getId() { return Id; }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDayText() {
        return DayText;
    }
    public void setTDayText(String DayText) {
        this.DayText = DayText;
    }

    public String getContentText() {
        return ContentText;
    }
    public void setContentText(String ContentText) {
        this.ContentText = ContentText;
    }


    Program(Integer id, String dayText, String contentText) {
        this.Id = id;
        this.DayText = dayText;
        this.ContentText = contentText;
    }
}
