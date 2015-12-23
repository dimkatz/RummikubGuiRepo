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
public class Player {
    
    public enum PlayerType{HUMAN, COMPUTER}
    
    private final PlayerType m_type;
    
    private final String m_name;
    
    private List<Tile> m_hand;
    
    boolean placedFirstSequence = true;

    public void setPlacedFirstSequence(boolean placedFirstSequence) {
        this.placedFirstSequence = placedFirstSequence;
    }

    public boolean isPlacedFirstSequence() {
        return placedFirstSequence;
    }

    public Player(PlayerType type, String name) {
        this.m_type = type;
        this.m_name = name;
        m_hand = new ArrayList<Tile>();
    }

    public String getName() {
        return m_name;
    }

    public PlayerType getType() {
        return m_type;
    }

    public void setHand(List<Tile> tiles) {
        this.m_hand = tiles;
    }

    public List<Tile> getTiles() {
        return m_hand;
    }
    
    public Tile drawTileByIndex(int i)
    {        
        if (i >= m_hand.size())
        {
            throw new  ArrayIndexOutOfBoundsException();
        }
        
        return m_hand.remove(i);
    }
    
    public void addTileToHand(Tile tile)
    {
        m_hand.add(tile);
    }
    
    public void addTileToHand(Tile tile, int index)
    {
        m_hand.add(index, tile);
    }
    
    public int remainingTilesCount()
    {
        return m_hand.size();
    }
}
