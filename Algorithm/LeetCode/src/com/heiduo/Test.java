package com.heiduo;

public class Test {
    public static void main(String[] args){
        Circle circle = new Circle(1, "name");
        Shape shape = circle;
        Circle circle2 = (Circle)shape;

        System.out.println("name:" + circle2.getName());
    }
}