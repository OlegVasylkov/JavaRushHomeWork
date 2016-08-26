package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by 309_newpower on 24.02.2016.
 */
public class BotClient extends Client
{
    public class BotSocketThread extends SocketThread
    {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": "))
            {
                String[] mass = message.split(": ");
                HashMap<String, SimpleDateFormat> map = new HashMap<>();
                map.put("дата", new SimpleDateFormat("d.MM.YYYY"));
                map.put("день", new SimpleDateFormat("d"));
                map.put("месяц", new SimpleDateFormat("MMMM"));
                map.put("год", new SimpleDateFormat("YYYY"));
                map.put("время", new SimpleDateFormat("H:mm:ss"));
                map.put("час", new SimpleDateFormat("H"));
                map.put("минуты", new SimpleDateFormat("m"));
                map.put("секунды", new SimpleDateFormat("s"));

                SimpleDateFormat dateFormat = map.get(mass[1]);
                if (dateFormat != null)
                    sendTextMessage(String.format("Информация для %s: %s", mass[0], dateFormat.format(Calendar.getInstance().getTime())));
            }

        }
    }

    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        return String.format("date_bot_%d", new Random().nextInt(100));
    }

    public static void main(String[] args)
    {
        new BotClient().run();
    }
}
