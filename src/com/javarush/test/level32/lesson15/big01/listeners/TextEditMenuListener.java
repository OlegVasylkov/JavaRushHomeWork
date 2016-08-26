package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by 309_newpower on 07.06.2016.
 */
public class TextEditMenuListener implements MenuListener
{
    private View view;

    public TextEditMenuListener(View view)
    {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e)
    {
        JMenu jMenu = (JMenu)e.getSource();
        Component[] mass = jMenu.getMenuComponents();
        for (Component component : mass){
            component.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e)
    {

    }

    @Override
    public void menuCanceled(MenuEvent e)
    {

    }
}
