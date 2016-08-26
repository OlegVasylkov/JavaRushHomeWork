package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by 309_newpower on 17.06.2016.
 */
public class FileBucket
{
    Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(null, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        File file = path.toFile();
        file.deleteOnExit();
    }

    public long getFileSize(){
        return path.toFile().length();
    }

    public void putEntry(Entry entry)
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(entry);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public Entry getEntry(){
        Entry entry = null;
        if (getFileSize() == 0) return entry;

        try(FileInputStream fileInputStream = new FileInputStream(path.toFile());
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            entry = (Entry) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return entry;
    }

    public void remove(){
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
