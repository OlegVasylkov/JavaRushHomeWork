package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 309_newpower on 28.01.2016.
 */
public class RaboraUaStategy implements Strategy
{
//    private static final String URL_FORMAT = "http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=junior+java&pg=%d";
    private static final String URL_FORMAT = "http://rabota.ua/jobsearch/vacancy_list?regionId=1&salaryType=1&keyWords=java&pg=%d";
    //regionId=1 - Киев

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> list = new ArrayList<>();
        int i = 1;
        while (true)
        {
            Document document = getDocument(searchString, i);
            Elements elements = document.getElementsByClass("v");
//            System.out.println(document);
            if (elements.size() > 0) i++;
            else break;

            for (Element element : elements)
            {
                Vacancy vacancy = new Vacancy();
                vacancy.setSiteName("http://rabota.ua/");
                //все сложно :) TODO
                vacancy.setCity(element.getElementsByClass("s").removeAttr("b").text());
                vacancy.setCompanyName(element.select("[class=\"s\"").text());
                vacancy.setTitle(element.select("[class=\"t\"]").text());
                vacancy.setUrl("http://rabota.ua" + element.select("[class=\"t\"]").attr("href"));
                vacancy.setSalary(element.select("[class=\"s\"]").first().getElementsByTag("b").text());

                list.add(vacancy);
            }
        }
        return list;
    }

    protected Document getDocument(String searchString, int page)
    {
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(URL_FORMAT, page))
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36")
                    .referrer("https://hh.ua/search/vacancy?text=java+searchString")
                    .get();
        }
        catch (IOException ignore) {}
        return doc;
    }
}
