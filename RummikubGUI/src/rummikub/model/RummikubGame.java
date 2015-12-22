/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import java.util.*;
/**
 *
 * @authors Guy Dunski Dmitry katz
 */
public class RummikubGame {
    
    private ArrayList<Player> players = new ArrayList<Player>();
    
    private Player currentPlayer;
    
    private Pile pile;
    
    private Board board;
    
    private String name; 
    
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Pile getPile() {
        return pile;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPile(Pile pile) {
        this.pile = pile;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void moveTileInBoard(int sourceIndex, int sourceTileIndex, int destIndex, int destTileIndex)
    {
        board.moveTile(sourceIndex, sourceTileIndex, destIndex, destTileIndex);
    }
    
    public void playTileFromHand(int handTileIndex, int destIndex, int destTileIndex)
    {
        Tile tileToplay = currentPlayer.drawTileByIndex(handTileIndex);
        
        try
        {                   
            board.insertTile(tileToplay, destIndex, destTileIndex);
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            currentPlayer.addTileToHand(tileToplay, handTileIndex);
            throw ex;
        }
    }
    
    public boolean validateGame()
    {
        return board.validate();
    }
    
    public boolean drawTile()
    {
        boolean succesfull = false;
        if (pile.size() > 0)
        {
            Tile drawn =  pile.Draw();
            currentPlayer.addTileToHand(drawn);
            succesfull = true;
        }
        
        return succesfull;
    }
    
    public void switchPlayer()
    {
        int nextPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
        currentPlayer = players.get(nextPlayerIndex);
    }
    
    public String isGameOver()
    {
        if (currentPlayer.getTiles().isEmpty()) 
        {
            return "The winner is: " + currentPlayer.getName();
        }
        else if(pile.size() == 0)
        {
            return "Game ended with a draw!";
        }
        else 
        {
            return null;
        }
    }
}