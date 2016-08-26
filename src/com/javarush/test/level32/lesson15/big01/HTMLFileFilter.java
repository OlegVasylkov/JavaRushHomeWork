package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by 309_newpower on 08.06.2016.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        return (f.isDirectory() || f.getName().toLowerCase().endsWith(".html") || f.getName().toLowerCase().endsWith(".htm"));
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}
