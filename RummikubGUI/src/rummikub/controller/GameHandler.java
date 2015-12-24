/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rummikub.model.*;

/**
 *
 * @author Guy
 */
public class GameHandler {
    
    private final static int MIN_PLAYER_NUM = 2;
    private final static int MAX_PLAYER_NUM = 4;
    
    private RummikubGame game;
    
    private TurnHandler turnHandler;
    
    private void playNewGame()
    {
        setNewGame();
        
        List<Tile> check = game.getPlayers().get(0).getTiles();
        
        Collections.sort(check);
        playGame();
    }
    
    private void loadGameFromFile()
    {
        String gameToLoadPath = "TODO...";
        RummikubGame gameToLoad;

        try 
        {
            gameToLoad = XMLHandler.loadGame(gameToLoadPath);
            this.game = gameToLoad;
        } 
        catch(FileNotFoundException ex)
        {
            //TODO ConsoleUtils.message("File not found!");
        }
        catch (Exception ex) 
        {
            //TODO ConsoleUtils.message("Unable to load file:!");
            return;
        }
        
        playGame();
    }
    
    private void playAnotherRound()
    {
        if (game == null) 
        {
            //ConsoleUtils.message("Previous game not found");
        }
        else
        {
            setNewRound();
            playGame();
        }
    }

    private void setNewGame() 
    {
        ArrayList<PlayerInfo> playersInfo = new ArrayList<PlayerInfo>();
        int numOfPlayers = 0;
        int numOfComputerPlayers = 0;
        String gameName;
        
        gameName = "TODO... Some Game...";
        
        for (int i = 0; i < numOfComputerPlayers; i++)
        {
            playersInfo.add(new PlayerInfo("com" + Integer.toString(i+1), Boolean.FALSE, Boolean.FALSE));
        }
        
        for (int i = numOfComputerPlayers; i < numOfPlayers; i++) {
            while(true)
            {
                String name = "TODO..." + i;
                PlayerInfo playerToAdd = new PlayerInfo(name, Boolean.TRUE, Boolean.FALSE);
                if (!playersInfo.contains(playerToAdd)) 
                {
                    playersInfo.add(playerToAdd);
                    break;
                }
                else
                {
                    //TODO ConsoleUtils.message("This name is already taken, try again.");
                }
            }
        }
        
        game = GameBuilder.createNewGame(playersInfo, gameName);
    }
    
    private void setNewRound()
    {
        GameBuilder.resetGame(game);
    }

    private void playGame() 
    {
        turnHandler = new TurnHandler();
        turnHandler.setGame(game);
        
        while(!turnHandler.isGameEnded())
        {
            turnHandler.playNextTurn();
        }
    }
}