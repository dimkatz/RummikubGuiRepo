/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import rummikub.model.*;

/**
 *
 * @author Guy
 */
public class TurnHandler 
{
    private final static String GAME_CLONE_PATH = "TempTurnStartStateGameClone.xml";
    
    private String lastSavePath;
    
    private boolean gameEnded = false;
    
    private RummikubGame game;

    public void setGame(RummikubGame game) 
    {
        this.game = game;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }
    
    public TurnHandler() 
    {
        gameEnded = false;
    }
    
    public void playNextTurn()
    {
        Player currentPlayer = game.getCurrentPlayer();
        
        //TODO: inform of current player turn
        
        if (currentPlayer.getType() == Player.PlayerType.HUMAN) 
        {
            humanTurn();
        }
        else
        {
            computerTurn();
        }
    }
    
    private void humanTurn() 
    {
        //TODO: save game if user wants to.
        
        playTurn();
    }

    private void computerTurn() 
    {
        playTurn();
    }
    
    private void playTurn()
    {
        //saving game for backup in case we need a clone
        try
        {
            XMLHandler.saveGame(game, GAME_CLONE_PATH);
        }
        catch(IOException | JAXBException | SAXException ex)
        {
            // TODO... ConsoleUtils.message("An internal save error has occured, terminating current game");
            gameEnded = true;
            return;
        }
        
        moveCycle();
        
        if (!game.validateGame()) 
        {
            IllegalBoardPenalty();
        }
        
        String gameOverMessage = game.isGameOver();
        if (gameOverMessage != null) 
        {
            //TODO: ConsoleUtils.message(gameOverMessage);
            gameEnded = true;
        }
        
        game.switchPlayer();
    }
    
    private void moveCycle() 
    {
        final boolean humanTurn = game.getCurrentPlayer().getType() == Player.PlayerType.HUMAN;
        boolean finishedMoves = false;
        Player currentPlayer = game.getCurrentPlayer();
        int initialPileSize = game.getPile().getTiles().size();
        int initialHandSize = currentPlayer.getTiles().size();
        
        if (humanTurn) 
        {
            while (!finishedMoves)
            {
                //TODO: update finished moves
                finishedMoves = false;
                
                if (finishedMoves) 
                {
                    if (currentPlayer.getTiles().size() == initialHandSize) 
                    {
                        //TODO: ConsoleUtils.message("No plays made, drawing from pile!");
                        drawFromPile();
                    }
                }
                else if (game.getPile().getTiles().size() == initialPileSize - 1) 
                {
                    finishedMoves = true;
                }
            }
        }
        else
        {
            boolean playedFromHand = AI.makeMove(game);
            if (playedFromHand)
            {
                // TODO: ConsoleUtils.message("computer played from hand: ");
            }
            else
            {
                // TODO: ConsoleUtils.message("computer drew from pile.");
            }
        }
    }
    
    private void moveTile()
    {
        //TODO: get move indices
        int[] moveIndices = null;
        
        int sourceRow = moveIndices[0];
        int sourceCol = moveIndices[1];
        int destRow = moveIndices[2] - 1;
        int destCol = moveIndices[3];
        
        if (sourceRow == 0) 
        {
            try
            {
                game.playTileFromHand(sourceCol, destRow, destCol);
            }
            catch(ArrayIndexOutOfBoundsException ex)
            {
                // TODO: ConsoleUtils.message("Unable to complete your play, wrong indices!");
            }
        }
        else
        {
            sourceRow--;
            try
            {
                game.moveTileInBoard(sourceRow, sourceCol, destRow, destCol);            
            }
            catch(ArrayIndexOutOfBoundsException ex)
            {
                // TODO: ConsoleUtils.message("Unable to complete your play, wrong indices!");
            }
        }
    }
    
    private void drawFromPile() 
    {
        setGameToTurnStartState();
        
        if (!game.drawTile()) 
        {
            // TODO: ConsoleUtils.message("Pile is empty!!!");
        }
    }

    private void IllegalBoardPenalty()
    {
        setGameToTurnStartState();
        
        // TODO: ConsoleUtils.message("Illegal board state, 3 tiles penalty!");
        game.drawTile();
        game.drawTile();
        game.drawTile();
    }
    
    private void setGameToTurnStartState()
    {
        RummikubGame gameClone;

        try 
        {
            gameClone = XMLHandler.loadGame(GAME_CLONE_PATH);
        } 
        catch (JAXBException | SAXException | IOException ex) 
        {
            // TODO: ConsoleUtils.message("An internal load error has occured, terminating current game");
            gameEnded = true;
            return;
        }

        game.setBoard(gameClone.getBoard());
        game.setPile(gameClone.getPile());
        game.setPlayers(gameClone.getPlayers());
        game.setCurrentPlayer(gameClone.getCurrentPlayer());
    }
    
    private void saveGame()
    {
        if (lastSavePath != null) 
        {
            try
            {
                XMLHandler.saveGame(game, lastSavePath);
            }
            catch( IOException | JAXBException | SAXException ex)
            {
                // TODO: ConsoleUtils.message("An error has occured while saving game to file");
            }
        }
        else
        {
            // TODO: ConsoleUtils.message("No last save file deteted, you need to save as... first!");
            saveGameAs();
        }
    }
    
    private void saveGameAs()
    {
        boolean gotLegalPath = false;

        while (!gotLegalPath) 
        {
            try 
            {
                
                String savePath = "TODO: get path from user";
                if(savePath.equals(GAME_CLONE_PATH))
                {
                    // TODO: ConsoleUtils.message("Change file name, this name is invalid");
                    continue;
                }
                
                XMLHandler.saveGame(game, savePath);
                lastSavePath = savePath;
                gotLegalPath = true;
            } 
            catch (FileNotFoundException ex) 
            {
                // TODO: ConsoleUtils.message("Illegal path, try again!");
            }
            catch (JAXBException | SAXException | IOException ex) 
            {
                // TODO: ConsoleUtils.message("An error has occured while saving game to file");
            }
        }
    }
}