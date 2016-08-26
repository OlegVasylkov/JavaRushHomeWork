package com.javarush.test.level21.lesson08.task02;


/* Клонирование
Класс Plant не должен реализовывать интерфейс Cloneable
Реализуйте механизм глубокого клонирования для Tree.
*/
public class Solution
{
    public static void main(String[] args)
    {
        Tree tree = new Tree("willow", null);
        Tree clone = null;
        try
        {
            clone = tree.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.branches);
        System.out.println(clone.branches);
    }

    public static class Plant
    {
        private String name;

        public Plant(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable
    {
        private String[] branches;

        public Tree(String name, String[] branches)
        {
            super(name);
            this.branches = branches;
        }

        public String[] getBranches()
        {
            return branches;
        }

        @Override
        public Tree clone() throws CloneNotSupportedException
        {
            String s = new String(super.getName());
            Tree clonedTree = null;
            if (getBranches() != null)
            {
                clonedTree = new Tree(s, new String[getBranches().length]);
                for (int i = 0; i < getBranches().length; i++) clonedTree.branches[i] = getBranches()[i];
            } else clonedTree = new Tree(s, null);
            return clonedTree;
        }
    }
}
