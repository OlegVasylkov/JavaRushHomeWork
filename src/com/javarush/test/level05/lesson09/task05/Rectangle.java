package com.javarush.test.level05.lesson09.task05;

/* Создать класс прямоугольник (Rectangle)
Создать класс прямоугольник (Rectangle). Его данными будут top, left, width, height (левая координата, верхняя, ширина и высота). Создать для него как можно больше конструкторов:
Примеры:
-	заданы 4 параметра: left, top, width, height
-	ширина/высота не задана (оба равны 0)
-	высота не задана (равно ширине) создаём квадрат
-	создаём копию другого прямоугольника (он и передаётся в параметрах)
*/

public class Rectangle
{
    private int top;
    private int left;
    private int width;
    private int height;

    public static void main (String[]args){}

    public Rectangle(int top, int left, int width, int height){
        this.top=top;
        this.left=left;
        this.width=width;
        this.height=height;
    }
    public Rectangle(int top, int left){
        this.top = top;
        this.left = left;
        this.width=this.height=0;
    }

    public Rectangle(int top, int left,int height)
    {
        this.top = top;
        this.left = left;
        this.width=this.height=height;
    }
    public Rectangle (Rectangle rectangle){
        this.width=rectangle.width;
        this.left= rectangle.left;
        this.top=rectangle.top;
        this.height= rectangle.height;
    }//Напишите тут ваш код

}
