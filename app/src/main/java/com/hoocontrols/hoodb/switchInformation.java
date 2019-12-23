package com.hoocontrols.hoodb;

public class switchInformation {
    private String type;
    private String name;

    public switchInformation(){

    }

    public switchInformation(String type, String name ){
        this.type = type;
        this.name = name;
    }

    public String getType(){
        return type;
    }
    public String getName() { return name; }


    public void setType(String type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
}
