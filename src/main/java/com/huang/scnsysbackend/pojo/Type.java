package com.huang.scnsysbackend.pojo;

public enum Type {

    PERSON("Person"),
    CITY("City"),
    STATE_OR_PROVINCE("State_Or_Province"),
    COUNTRY("Country"),
    EMAIL("Email"),
    TITLE("Title"),
    ORGANIZATION("Organization"),
    LOCATION("Location");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getName() {
        return type;
    }
}