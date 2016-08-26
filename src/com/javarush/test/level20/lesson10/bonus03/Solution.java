package com.javarush.test.level20.lesson10.bonus03;


import java.util.ArrayList;
import java.util.List;


/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<String> words = new ArrayList<String>();
        words.add("ho");
        words.add("same");
        words.add("plm");
        words.add("fulm");
        words.add("rek");
        words.add("gsf");
        words.add("rj");
        //words.add("rrj");
        words.add("mglp");
        words.add("jhvok");
        words.add("orgn");
        words.add("ranm");
        for (String word : words)
        {
            detectAllWords(crossword, word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words)
    {
        List<Word> list = new ArrayList<>();
        for (String word : words)
        {
            Word w = new Word(word);
            int[] firstLetter = null;
            int[] lastLetter = null;
            for (int y = 0; (y < crossword.length) | (lastLetter != null); y++)
            {
                for (int x = 0; x < crossword[0].length; x++)
                {
                    if (word.codePointAt(0) == crossword[y][x])
                    {
                        firstLetter = new int[]{x, y};
                        lastLetter = findNextLetter(crossword, word, x, y, 0, 0);
                    }
                    if (lastLetter != null) break;
                }
                if (lastLetter != null) break;
            }

            w.setStartPoint(firstLetter[0], firstLetter[1]);
            w.setEndPoint(lastLetter[0], lastLetter[1]);
            list.add(w);
            System.out.println(w);
        }
        return list;
    }

    public static int[] findNextLetter(int[][] crossword, String word, int x, int y, int a, int b)
    {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(new Direction(-1, -1));
        directions.add(new Direction(-1, 0));
        directions.add(new Direction(-1, 1));
        directions.add(new Direction(0, -1));
        directions.add(new Direction(0, 1));
        directions.add(new Direction(1, -1));
        directions.add(new Direction(1, 0));
        directions.add(new Direction(1, 1));

        int[] result = null;
        if (a == 0 && b == 0)
        {
            for (Direction direction : directions){
                try
                {
                    if (crossword[y + direction.y][x + direction.x] == (int)word.charAt(1))
                    {
                        if (word.length()>1)
                        {
                            result = findNextLetter(crossword, word.substring(1), x + direction.x, y + direction.y, direction.x, direction.y);
                        }
                        else
                            result = new int[]{x+direction.x, y+direction.y};
                        break;
                    }
                }catch(Exception e){
                    continue;
                }
            }
        } else
        {
            if (word.length()>1)
            {
                if (word.codePointAt(1) == crossword[y + b][x + a])
                    result = findNextLetter(crossword, word.substring(1), x+a, y+b, a, b);
            }else result = new int[]{x, y};
        }
        return result;
    }
    public static class Direction {
        int x;
        int y;

        public Direction (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Word
    {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text)
        {
            this.text = text;
        }

        public void setStartPoint(int i, int j)
        {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j)
        {
            endX = i;
            endY = j;
        }

        @Override
        public String toString()
        {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
