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
public class Board {
    private final int MAX_SEQUENCE_AMOUNT = 30;
    
    private ArrayList<Sequence> sequences = new ArrayList<Sequence>(MAX_SEQUENCE_AMOUNT);

    public Board() 
    {
        for (int i = 0; i < MAX_SEQUENCE_AMOUNT; i++) 
        {
            sequences.add(new Sequence());
        }
    }

    public ArrayList<Sequence> getSequences() {
        return sequences;
    }

    public void moveTile(int sourceIndex, int sourceTileIndex , int destIndex, int destTileIndex)
    {
        Sequence sourceSequence = sequences.get(sourceIndex);
        Sequence destSequence = sequences.get(destIndex);   
        
        Tile tileToMove = sourceSequence.removeTile(sourceTileIndex);
        try
        {
            destSequence.AddTile(destTileIndex, tileToMove);     
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            sourceSequence.AddTile(sourceIndex, tileToMove);
            throw ex;
        }
    }

    public void insertTile(Tile tileToplay, int destIndex, int destTileIndex)
    {
        Sequence destSequence = sequences.get(destIndex);
        destSequence.AddTile(destTileIndex ,tileToplay);
    }

    boolean validate()
    {
        boolean isValid = true;
        
        for (Sequence sequence : sequences) {
            if (sequence.Size() != 0)
            {
                if (!sequence.validate())
                {
                    isValid = false;
                    break;
                }
            }
        }
        
        return isValid;
    }
}
