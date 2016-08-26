package com.javarush.test.level27.lesson15.big01.ad;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 309_newpower on 05.01.2016.
 */
class AdvertisementStorage
{
    private final List<Advertisement> videos = new LinkedList<>();

    private static AdvertisementStorage instance;

    private AdvertisementStorage()
    {
        Object someContent = new Object();
//        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
//        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Third Video", 500, 10, 10 * 60));   //10 min
//        videos.add(new Advertisement(someContent, "100-60", 100, 1, 60));
//        videos.add(new Advertisement(someContent, "100-59", 100, 1, 59));
//        videos.add(new Advertisement(someContent, "50-36", 50, 1, 36));
//        videos.add(new Advertisement(someContent, "50-24", 50, 1, 24));
//        videos.add(new Advertisement(someContent, "50-12", 30, 1, 12));
//        videos.add(new Advertisement(someContent, "50-12", 10, 1, 12));
//        videos.add(new Advertisement(someContent, "50-12", 10, 1, 12));

//        videos.add(new Advertisement(someContent, "1", 152, 3, 3 * 60));
//        videos.add(new Advertisement(someContent, "2", 5, 2, 5 * 60));
//        videos.add(new Advertisement(someContent, "3", 3, 2, 3 * 60));
//        videos.add(new Advertisement(someContent, "4", 99, 10, 2 * 60));
        videos.add(new Advertisement(someContent, "a", 2000, 3, 6 * 60));
        videos.add(new Advertisement(someContent, "B", 150, 3, 3 * 60));
        videos.add(new Advertisement(someContent, "c", 100, 1, 15 * 60));
        videos.add(new Advertisement(someContent, "в", 400, 1, 10 * 60));
        videos.add(new Advertisement(someContent, "г", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "д", 100, 1, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "12", 400, 2, 10 * 60));   //10 min
    }

    public static AdvertisementStorage getInstance()
    {
        if (instance == null)
            synchronized (AdvertisementStorage.class){
                if (instance == null)
                    instance = new AdvertisementStorage();
            }
        return instance;
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
