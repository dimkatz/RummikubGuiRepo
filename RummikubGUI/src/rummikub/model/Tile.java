/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.*;

/**
 *
 * @author Guy
 */
public class Tile implements Comparable<Tile> {

    public enum Color implements Comparable<Color> {

        BLACK(1),
        RED(2),
        BLUE(3),
        YELLOW(4);
        
        private Integer value;

        private Color(Integer value)
        {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

    }

    private final Color m_color;

    private final Integer m_value;

    public Color getColor() {
        return m_color;
    }

    public int getValue() {
        return m_value;
    }

    private Tile(Color color, int value) {
        this.m_color = color;
        this.m_value = value;
    }

    private static final List<Tile> tileSet = new ArrayList<Tile>();

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 13;

    static {
        for (Color color : Color.values()) {
            for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
                tileSet.add(new Tile(color, i));
                tileSet.add(new Tile(color, i));
            }
        }

        //adding the Joker: represented by 0
        tileSet.add(new Tile(Color.RED, 0));
        tileSet.add(new Tile(Color.BLACK, 0));
    }

    public static ArrayList<Tile> newTileSet() {
        return new ArrayList<Tile>(tileSet);
    }

    @Override
    public int compareTo(Tile other) 
    {
        int res;
        
        if (this.m_color == other.m_color) {
            res = this.m_value.compareTo(other.getValue());
        } 
        else 
        {
            res = this.getColor().getValue().compareTo(other.getColor().getValue());
        }
        
       return res;
    }

    public boolean isJoker() {
        return m_value == 0;
    }

}
