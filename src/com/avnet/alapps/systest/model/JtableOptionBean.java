package com.avnet.alapps.systest.model;

public class JtableOptionBean {
	private String displayText;
    private String value;
    
    public JtableOptionBean(String displayText, String value) {
        this.displayText = displayText;
        this.value = value;
    }


    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
