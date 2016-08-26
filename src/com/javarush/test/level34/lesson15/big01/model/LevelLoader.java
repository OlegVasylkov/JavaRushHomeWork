package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public class LevelLoader
{
    private Path levels;
    public LevelLoader(Path levels){
        this.levels = levels;
    }

    public GameObjects getLevel(int level){
        int lev = level % 60;
        if (lev == 0) {
            lev = 60;
        }

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(0, 0);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(levels.toFile()))) {
            int cellSize = Model.FIELD_SELL_SIZE;
            int x0 = cellSize / 2;
            int y0 = cellSize / 2;
            while (bufferedReader.ready())
            {
                if (bufferedReader.readLine().equals("Maze: " + lev)) {
                    bufferedReader.readLine();
                    int x = Integer.parseInt(bufferedReader.readLine().substring(8));
                    int y = Integer.parseInt(bufferedReader.readLine().substring(8));
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    for (int i = 0; i < y; i++) {
                        String read = bufferedReader.readLine();
                        for (int j = 0; j < x; j++)
                        {
                            int X = x0 + j * cellSize;
                            int Y = y0 + i * cellSize;
                            switch (read.charAt(j))
                            {
                                case 'X':
                                    walls.add(new Wall(X, Y));
                                    break;
                                case '@':
                                    player = new Player(X, Y);
                                    break;
                                case '*':
                                    boxes.add(new Box(X, Y));
                                    break;
                                case '.':
                                    homes.add(new Home(X, Y));
                                    break;
                                case '&':
                                    boxes.add(new Box(X, Y));
                                    homes.add(new Home(X, Y));
                                    break;
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
