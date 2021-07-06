package com.heiduo;

public class Circle extends Shape{
    private String name;

    public Circle(int type,String name){
        super(type);
        this.name = name;
    }

    public String getName(){
        return name;
    }
}