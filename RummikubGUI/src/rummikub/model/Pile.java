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
public class Pile {
    
    private ArrayList<Tile>  tiles = new ArrayList<>();

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    
    public Pile(ArrayList<Tile> tileSet)
    {
        this.tiles = tileSet;
        Collections.shuffle(tiles);
    }
    
    public Tile Draw()
    {
        Tile drawn = null;
        if (tiles.size() > 0) {
            drawn = tiles.remove(0);
        }
        
        return drawn;
    }

    int size() {
        return tiles.size();
    }
}
