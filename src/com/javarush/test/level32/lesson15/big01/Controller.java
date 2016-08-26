package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by 309_newpower on 06.06.2016.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view)
    {
        this.view = view;
    }

    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }

    public static void main(String[] args)
    {
        View view1 = new View();
        Controller controller = new Controller(view1);
        view1.setController(controller);
        view1.init();
        controller.init();
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    public void resetDocument(){
        UndoListener undoListener = view.getUndoListener();
        if (document != null)
            document.removeUndoableEditListener(undoListener);
        document = (HTMLDocument)new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(undoListener);
        view.update();
    }

    public void setPlainText(String text){
        resetDocument();
        try(StringReader reader = new StringReader(text)) {
            new HTMLEditorKit().read(reader, document, 0);
        }
        catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        }
        catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int result = jFileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try(FileReader reader = new FileReader(currentFile)){
                new HTMLEditorKit().read(reader, document, 0);
            }
            catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }

    public void saveDocument()
    {
        if(currentFile == null)
            saveDocumentAs();
        else {
            view.selectHtmlTab();
            try(FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }catch (BadLocationException|IOException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        jFileChooser.setDialogTitle("Save File");
        int result = jFileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try(FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }catch (BadLocationException|IOException e) {
                ExceptionHandler.log(e);
            }
        }
    }
}
