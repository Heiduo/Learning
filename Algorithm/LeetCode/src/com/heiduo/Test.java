package com.heiduo;

public class Test {
    public static void main(String[] args){
        Circle circle = new Circle(1, "name");
        Shape shape = circle;
        Circle circle2 = (Circle)shape;

        System.out.println("name:" + circle2.getName());

        int x = -65535;
        byte x0,x1;
        x0 = (byte) 0xff;
        x1 = (byte) 0xff;
        int y = ((x0 &0xff)<<8) + (x1&0xff);

        System.out.println("y:" + y);
        System.out.println("y:" + Integer.toHexString(y));
        y = (y<<16)>>16;
        System.out.println("y:" + y);
        System.out.println("y:" + Integer.toHexString(y));

        String name = "test.txt";
        name = name.substring(0,name.indexOf('.'));
        System.out.println("name:" + name);
    }
}