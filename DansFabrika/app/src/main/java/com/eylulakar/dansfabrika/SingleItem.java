package com.eylulakar.dansfabrika;

import java.io.Serializable;

/**
 * Created by Eylul on 20.10.2014.
 */
public class SingleItem implements Serializable {

    private String KeyValue;
    private String TextValue;

    public String getKeyValue() {
        return KeyValue;
    }
    public void setKeyValue(String KeyValue) {
        this.KeyValue = KeyValue;
    }

    public String getTextValue() { return TextValue;}
    public void setTextValue(String TextValue) {
        this.TextValue = TextValue;
    }

    SingleItem(String KeyValue, String TextValue) {
        this.KeyValue = KeyValue;
        this.TextValue = TextValue;
    }
}
