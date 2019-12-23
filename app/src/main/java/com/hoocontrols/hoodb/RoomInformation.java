package com.hoocontrols.hoodb;

public class RoomInformation {
    private String name;

    public RoomInformation(){

    }

    public RoomInformation(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
