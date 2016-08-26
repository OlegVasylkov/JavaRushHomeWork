package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by 309_newpower on 06.06.2016.
 */
public class View extends JFrame implements ActionListener
{
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private Controller controller;
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }
        catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
        catch (InstantiationException e) {
            ExceptionHandler.log(e);
        }
        catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Новый"))
            controller.createNewDocument();
        else if(actionCommand.equals("Открыть"))
            controller.openDocument();
        else if(actionCommand.equals("Сохранить"))
            controller.saveDocument();
        else if(actionCommand.equals("Сохранить как..."))
            controller.saveDocumentAs();
        else if(actionCommand.equals("Выход"))
            controller.exit();
        else if(actionCommand.equals("О программе"))
            showAbout();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }
    public void exit(){
        controller.exit();
    }

    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        //Добавлять в верхнюю часть панели контента
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        tabbedPane.add("HTML", htmlTextPane);
        tabbedPane.add("Текст", plainTextPane);
        tabbedPane.setPreferredSize(new Dimension(800, 600));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
        int index = tabbedPane.getSelectedIndex();
        if (index == 0)
            controller.setPlainText(plainTextPane.getText());
        else if(index == 1)
            plainTextPane.setText(controller.getPlainText());
        resetUndo();
    }

    public boolean canUndo()
    {
        return undoManager.canUndo();
    }

    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    public void undo(){
        undoManager.undo();
    }

    public void redo(){
        undoManager.redo();
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex()==0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(
                getContentPane(),
                "Info about this program",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
