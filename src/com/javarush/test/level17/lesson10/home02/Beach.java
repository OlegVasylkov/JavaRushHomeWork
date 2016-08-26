package com.javarush.test.level17.lesson10.home02;

/* Comparable
Реализуйте интерфейс Comparable<Beach> в классе Beach, который будет использоваться нитями.
*/

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public int compareTo(Beach o)
    {
        /*if(getQuality() != o.getQuality()) return (getQuality() - o.getQuality())*10000;
        else if (getDistance() != o.getDistance()) return (int)(1000*(getDistance() - o.getDistance()));
        else return getName().compareTo(o.getName());
        */
        int distanceParam = (int) (distance - o.getDistance());
        int qualityParam = quality - o.getQuality();
        return name.compareTo(o.getName()) + 100 * distanceParam + 10000 * qualityParam;
    }

}
