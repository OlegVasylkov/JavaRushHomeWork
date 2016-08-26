package com.javarush.test.level32.lesson15.big01.actions;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by 309_newpower on 07.06.2016.
 */
public class UndoAction extends AbstractAction
{
    private View view;

    public UndoAction(View view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        view.undo();
    }
}