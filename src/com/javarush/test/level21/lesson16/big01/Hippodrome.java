package com.javarush.test.level21.lesson16.big01;


import java.util.ArrayList;

/**
 * Created by 309_newpower on 10.11.2015.
 */
public class Hippodrome
{
    protected ArrayList<Horse> horses = new ArrayList<Horse>();
    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public void move(){
        for (Horse horse : horses)
            horse.move();
    }
    public void run() throws InterruptedException
    {
        for (int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void print(){
        for (Horse horse : horses)
        {
            horse.print();
            System.out.println();
        }
    }
    public Horse getWinner(){
        Horse winner = horses.get(0);
        for (Horse horse : horses){
           if (horse.getDistance() > winner.getDistance()) winner = horse;
        }
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() +"!");
    }

    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();
        Horse horse1 = new Horse("horse1", 3.0, 0.0);
        Horse horse2 = new Horse("horse2", 3.0, 0.0);
        Horse horse3 = new Horse("horse3", 3.0, 0.0);
        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);
        game.run();
        game.printWinner();
    }
}
