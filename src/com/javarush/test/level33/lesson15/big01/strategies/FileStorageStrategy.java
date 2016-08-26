package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by 309_newpower on 17.06.2016.
 */
public class FileStorageStrategy implements StorageStrategy
{
    private FileBucket[] table = {new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket()};

    private long bucketSizeLimit = 10000;

    private int hash(Long k)
    {
        return k.hashCode();
    }

    private int indexFor(int h, int length)
    {
        return h & (length - 1);
    }

    Entry getEntry(Long key)
    {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
//        int hash = hash(key);
//        int index = indexFor(hash, table.length);
//        if (table[index] != null) {
//            Entry entry = table[index].getEntry();
//            while (entry != null) {
//                if (entry.getKey().equals(key)) {
//                    return entry;
//                }
//                entry = entry.next;
//            }
//        }
//        return null;
    }

    private void resize(int newCapacity)
    {
        FileBucket[] oldTable = table;
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (int i = 0; i < newCapacity; i++)
            newTable[i] = new FileBucket();
        transfer(newTable);
        table = newTable;
        for (FileBucket fileBucket : oldTable)
        {
            fileBucket.remove();
            fileBucket = null;
        }
    }

    private void transfer(FileBucket[] newTable)
    {
        int newCapacity = newTable.length;
        for (FileBucket fileBucket : table)
        {
            while (fileBucket != null)
            {
                Entry entry = fileBucket.getEntry();
                while (entry != null)
                {
                    Entry next = entry.next;
                    int newIndex = indexFor(entry.hash, newCapacity);
                    entry.next = newTable[newIndex].getEntry();
                    newTable[newIndex].putEntry(entry);
                    entry = next;
                }
            }
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex)
    {
        createEntry(hash, key, value, bucketIndex);
        if (table[bucketIndex].getFileSize() >= bucketSizeLimit)
            resize(2 * table.length);

    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
    }

    public long getBucketSizeLimit()
    {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit)
    {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

//    @Override
//    public boolean containsValue(String value)
//    {
//        if (value == null)
//            return false;
//        for (FileBucket fileBucket : table)
//        {
//            if (fileBucket == null || fileBucket.getEntry() == null)
//                return false;
//            if (fileBucket.getEntry().getValue().equals(value))
//                return true;
//        }
//        return false;
//    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null)
            return false;
        for (FileBucket f : table)
        {
            Entry aTable = f.getEntry();
            for (Entry e = aTable; e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        }
        return false;
    }


    @Override
    public void put(Long key, String value)
    {
        if (key == null) return;
        int hash = hash(key);
        int i = indexFor(hash, table.length);

        for (Entry e = table[i].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, i);

    }

    @Override
    public Long getKey(String value)
    {
        if (value == null)
            return null;
        for (FileBucket fileBucket : table)
        {
            Entry e = fileBucket.getEntry();
            if (e != null && e.getValue().equals(value)) return e.getKey();
        }
        return null;
    }
    @Override
    public String getValue(Long key) {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }
}

//public class FileStorageStrategy implements StorageStrategy
//{
//    private FileBucket[] table = {new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
//            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
//            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
//            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket()};
//    private long bucketSizeLimit = 10000;
//
//    public long getBucketSizeLimit()
//    {
//        return bucketSizeLimit;
//    }
//
//    public void setBucketSizeLimit(long bucketSizeLimit)
//    {
//        this.bucketSizeLimit = bucketSizeLimit;
//    }
//
//    private int hash(Long k)
//    {
//        return k.hashCode();
//    }
//
//    private int indexFor(int hash, int length)
//    {
//        return hash & (length - 1);
//    }
//
//    private Entry getEntry(Long key)
//    {
//        int hash = (key == null) ? 0 : hash(key);
//        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next)
//        {
//            Object k;
//            if (e.hash == hash &&
//                    ((k = e.key) == key || (key != null && key.equals(k))))
//                return e;
//        }
//        return null;
//    }
//
//    private void transfer(FileBucket[] newTable)
//    {
//        FileBucket[] src = table;
//        int newCapacity = newTable.length;
//        for (int j = 0; j < src.length; j++)
//        {
//            Entry e = src[j].getEntry();
//            if (e != null)
//            {
//                do
//                {
//                    Entry next = e.next;
//                    int i = indexFor(e.hash, newCapacity);
//                    e.next = newTable[i].getEntry();
//                    newTable[i].putEntry(e);
//                    e = next;
//                }
//                while (e != null);
//            }
//        }
//    }
//
//    private void resize(int newCapacity)
//    {
//        FileBucket[] newTable = new FileBucket[newCapacity];
//        for (int i = 0; i < newTable.length; i++)
//            newTable[i] = new FileBucket();
//        transfer(newTable);
//        for (FileBucket fileBucket : table)
//        {
//            fileBucket.remove();
//            fileBucket = null;
//        }
//        table = newTable;
//    }
//
//    private void createEntry(int hash, Long key, String value, int bucketIndex)
//    {
//        Entry e = table[bucketIndex].getEntry();
//        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
//    }
//
//    private void addEntry(int hash, Long key, String value, int bucketIndex)
//    {
//        createEntry(hash, key, value, bucketIndex);
//        if (table[bucketIndex].getFileSize() >= bucketSizeLimit)
//            resize(2 * table.length);
//    }
//
//    @Override
//    public boolean containsKey(Long key)
//    {
//        return getEntry(key) != null;
//    }
//
//    @Override
//    public boolean containsValue(String value)
//    {
//        if (value == null)
//            return false;
//        for (FileBucket f : table)
//        {
//            Entry aTable = f.getEntry();
//            for (Entry e = aTable; e != null; e = e.next)
//                if (value.equals(e.value))
//                    return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void put(Long key, String value)
//    {
//        if (key == null) return;
//        int hash = hash(key);
//        int i = indexFor(hash, table.length);
//        for (Entry e = table[i].getEntry(); e != null; e = e.next)
//        {
//            Object k;
//            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
//            {
//                e.value = value;
//                return;
//            }
//        }
//        addEntry(hash, key, value, i);
//    }
//
//    @Override
//    public Long getKey(String value)
//    {
//        if (value == null)
//            return null;
//        for (FileBucket f : table)
//        {
//            Entry aTable = f.getEntry();
//            for (Entry e = aTable; e != null; e = e.next)
//                if (value.equals(e.value))
//                    return aTable.getKey();
//        }
//        return null;
//    }
//
//    @Override
//    public String getValue(Long key)
//    {
//        Entry entry = getEntry(key);
//        if (entry != null) return entry.value;
//        return null;
//    }
//}