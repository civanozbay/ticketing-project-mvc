package com.cydeo.enums;

public enum Status {
    OPEN("Open"),IN_PROGRESS("In progress"),COMPLETE("Complete");

    private final String value;

    private Status (String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
