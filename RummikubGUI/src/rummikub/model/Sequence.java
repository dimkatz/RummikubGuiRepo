/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.*;
/**
 *
 * @author DK
 */
public class Sequence
{
    public static final int MIN_LEGAL_LEN = 3;
    public static final int MAX_CONST_LEGAL_LEN = 4;
    
    private ArrayList<Tile> tiles = new ArrayList<Tile>(); 

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    
    public void  AddTile(int index, Tile toAdd)
    {
        if (tiles.size() < index)
        {
            throw  new ArrayIndexOutOfBoundsException();
        }
        
        tiles.add(index ,toAdd);
    }

    public int Size()
    {
        return tiles.size();
    }

    Tile removeTile(int index)
    {
        if (tiles.size() <= index)
        {
            throw  new ArrayIndexOutOfBoundsException();
        }
        
        return tiles.remove(index);
    }
    
    public boolean validate()
    {
        return tiles.size() >= MIN_LEGAL_LEN &&
               ( validateAssendingSeries() || validateConstSeries() );
    }

    private boolean validateConstSeries()
    {
        ArrayList<Tile.Color> seenColors = new ArrayList<>();
        boolean isLegal = true;
        int value = 0;
        boolean valueSet = false;
        if  (tiles.size() > MAX_CONST_LEGAL_LEN)
        {
            isLegal = false;
        }
        else
        {
            for (Tile currTile : tiles) {
                if (!currTile.isJoker()) {
                    if (!valueSet) {
                        value = currTile.getValue();
                        valueSet = true;
                    }

                    if (currTile.getValue() != value || seenColors.contains(currTile.getColor())) {
                        isLegal = false;
                        break;
                    }
                    
                    seenColors.add(currTile.getColor());
                }
            }
        }
        
        return isLegal;
    }

    private boolean validateAssendingSeries()
    {
        boolean isLegal = true;
        int lastValue = 0;
        Tile.Color color = Tile.Color.BLACK;
        boolean valueSet = false;
        
        for (int i = 0; i < tiles.size(); i++) 
        {
            Tile currTile = tiles.get(i);
            
            if (currTile.isJoker())
            {
                if (lastValue == Tile.MAX_VALUE) {
                    isLegal = false;
                    break;
                }
                if (valueSet){
                    lastValue++;
                }
            }
            else
            {
                if (currTile.getValue() == Tile.MIN_VALUE && i > 0){
                    isLegal = false;
                    break;
                }
                else
                {
                    if (!valueSet)
                    {
                        lastValue = currTile.getValue();
                        color = currTile.getColor();
                        valueSet = true;
                    }
                    else if (currTile.getValue() == lastValue + 1 && currTile.getColor() == color){
                        lastValue++;
                    }
                    else
                    {
                        isLegal = false;
                        break;
                    } 
                }       
            }    
        }
        
        return isLegal;
    }
    
    
    
    
}
