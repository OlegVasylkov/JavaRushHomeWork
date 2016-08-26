package com.javarush.test.level30.lesson15.big01;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by 309_newpower on 08.02.2016.
 */
public class Connection implements Closeable
{
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException
    {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException
    {
        synchronized (out)
        {
            if (message.getData() == null) out.writeObject("null");
            else out.writeObject(message.getData());
            out.writeObject(message.getType());
        }
    }

    public Message receive() throws IOException, ClassNotFoundException
    {
        synchronized (in)
        {
            String data = (String) in.readObject();
            MessageType type = (MessageType) in.readObject();
            if (data.equals("null"))
                return new Message(type);
            else
                return new Message(type, data);
        }
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException
    {
        in.close();
        out.close();
        socket.close();
    }
}
