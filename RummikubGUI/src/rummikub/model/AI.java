/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author DK
 */
public class AI
{
    public static Boolean makeMove(RummikubGame game)
    {
        boolean played = false;
        Player player = game.getCurrentPlayer();
        
        List<Tile> sequenceToPlay = findPlayableSequence(player.getTiles());
        
        if (!sequenceToPlay.isEmpty())
        {
            played = playSequence(sequenceToPlay, game);
        }
        else
        {
            game.drawTile();
        }
        
        return played;
    }

    private static List<Tile> findPlayableSequence(List<Tile> tiles)
    {
        List<Tile> sequenceToPlay = new ArrayList<>();
        
        Collections.sort(tiles);
        
        Tile.Color lastColor = tiles.get(0).getColor();
        int lastValue = 0;
        
        for (Tile tile : tiles) {
            if (tile.getValue() == lastValue + 1 && tile.getColor() == lastColor)
            {
                lastValue++;
            }
            else
            {
                if (sequenceToPlay.size() >= Sequence.MIN_LEGAL_LEN)
                {
                    break;
                }
                else
                {
                    sequenceToPlay.clear();
                    lastValue = tile.getValue();
                    lastColor = tile.getColor();
                }
            }
            
            sequenceToPlay.add(tile);
        }
        
        if (sequenceToPlay.size() < Sequence.MIN_LEGAL_LEN)
        {
            sequenceToPlay.clear();
        }
        
        return sequenceToPlay;
    }
    

    private static Tile findJoker(List<Tile> tiles)
    {
        Tile joker = null;
        
        for (Tile tile : tiles) {
            if (tile.isJoker())
            {
                joker = tile;
            }
        }
        
        return joker;
    }


    private static boolean playSequence(List<Tile> sequenceToPlay, RummikubGame game) 
    {
        boolean played = false;
        List<Sequence> sequences = game.getBoard().getSequences();
        List<Tile> handTiles = game.getCurrentPlayer().getTiles();
        
        for (Sequence sequence : sequences) {
            if (sequence.Size() == 0)
            {   
                for (Tile tileToPlace : sequenceToPlay) {
                    handTiles.remove(tileToPlace);
                    sequence.getTiles().add(tileToPlace);
                }
                played = true;
                break;
                
            }
        }
        
        return played;
    }
    
    
    
    
}
