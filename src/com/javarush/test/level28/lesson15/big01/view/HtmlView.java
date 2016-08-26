package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

/**
 * Created by 309_newpower on 25.01.2016.
 */
public class HtmlView implements View
{
    private final String filePath = "./src/"+ this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (IOException e) {
            System.out.println("Some exception occurred");
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
//        controller.onCitySelect("kiev");
        controller.onCitySelect("junior+kiev");
    }

    private String getUpdatedFileContent(List<Vacancy> list) throws IOException
    {
        Document document = getDocument();
        Element element = document.getElementsByClass("template").first();
        Element elementClone = element.clone();
        elementClone.removeAttr("style");
        elementClone.removeClass("template");

        document.select("tr[class=\"vacancy\"]").not("tr[class\"template\"]").remove();

        for (Vacancy vacancy : list){
            Element vac = elementClone.clone();
            vac.getElementsByClass("city").first().text(vacancy.getCity());
            vac.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
            vac.getElementsByClass("salary").first().text(vacancy.getSalary());
            vac.select("a")
                    .first().text(vacancy.getTitle())
                    .attr("href", vacancy.getUrl());
            element.before(vac.outerHtml());
        }
        return document.html();
    }

    private void updateFile(String string) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(string);
        fileWriter.close();

    }

    protected Document getDocument() throws IOException{
        Document document = Jsoup.parse(new File(filePath), "UTF-8");
        return document;
    }
}
