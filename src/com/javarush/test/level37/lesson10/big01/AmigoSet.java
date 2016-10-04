package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


/**
 * Created by 309_newpower on 21.09.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet()
    {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection){
        this.map = new HashMap<>((int)Math.max(16,collection.size()/.75f));
        this.addAll(collection);
    }



    @Override
    public boolean add(E e)
    {
        return map.put(e,PRESENT) != null;
    }

    @Override
    public Iterator iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return map.keySet().contains(o);
    }

    @Override
    public void clear()
    {
        map.clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return map.remove(o) != null;
    }

    @Override
    public Object clone()
    {
        AmigoSet<E> clone;
        try {
            clone = new AmigoSet<>();
            clone.map = new HashMap<>(map);
        } catch (Exception e) {
            throw new InternalError();
        }

        return clone;
    }

    private void writeObject(ObjectOutputStream s) throws IOException{
        s.defaultWriteObject();
        s.writeObject(map.size());
        for(E e : map.keySet()){
            s.writeObject(e);
        }
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));

    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        s.defaultReadObject();

        int size = (int)s.readObject();
        Set<E> set = new HashSet<>();
        for(int i =0;i<size;i++){
            set.add((E)s.readObject());
        }
        int capacity = (int)s.readObject();
        float loadFactor = (float)s.readObject();
        map = new HashMap<>(capacity,loadFactor);
        for(E e:set){
            map.put(e,PRESENT);
        }
    }
}
