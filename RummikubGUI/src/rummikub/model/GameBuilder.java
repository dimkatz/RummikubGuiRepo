/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.model;

import generated.Rummikub;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Guy
 */
public class GameBuilder {

    private static final int STARTING_HAND_TILE_COUNT = 14;

    public static RummikubGame createNewGame(List<PlayerInfo> playersInfo, String name) {
        RummikubGame game = new RummikubGame();

        game.setName(name);
        game.setBoard(new Board());
        game.setPile(new Pile(Tile.newTileSet()));
        game.setPlayers(setPlayersList(playersInfo));
        Collections.shuffle(game.getPlayers());
        game.setCurrentPlayer(game.getPlayers().get(0));
        dealTiles(game);

        return game;
    }

    private static void dealTiles(RummikubGame game) {
        ArrayList<Player> playersList = game.getPlayers();

        for (Player player : playersList) {
            for (int i = 0; i < STARTING_HAND_TILE_COUNT; i++) {
                player.addTileToHand(game.getPile().Draw());
            }
        }
    }

    private static ArrayList<Player> setPlayersList(List<PlayerInfo> playersInfo) {
        ArrayList<Player> playersList = new ArrayList<Player>();

        for (PlayerInfo playerInfo : playersInfo) {
            Player.PlayerType type = playerInfo.isHumen() ? Player.PlayerType.HUMAN : Player.PlayerType.COMPUTER;
            
            Player player = new Player(type, playerInfo.getName());
            player.setPlacedFirstSequence(playerInfo.isPlacedFirstSequence());
            
            playersList.add(player);
        }

        return playersList;
    }

    public static void resetGame(RummikubGame game) {
        game.setBoard(new Board());
        game.setPile(new Pile(Tile.newTileSet()));
        Collections.shuffle(game.getPlayers());
        game.setCurrentPlayer(game.getPlayers().get(0));
        for (Player player : game.getPlayers()) {
            player.getTiles().clear();
        }
        dealTiles(game);
    }

    public static RummikubGame createExistingGame(generated.Rummikub rummikub) {
        RummikubGame game = new RummikubGame();
        
        game.setName(rummikub.getName());
        game.setBoard(new Board());
        game.setPile(new Pile(Tile.newTileSet()));

        game.setPlayers(setPlayersList(createPlayerInfo(rummikub.getPlayers())));

        game.setCurrentPlayer(game.getPlayers().stream().
                filter(player -> player.getName().equals(rummikub.getCurrentPlayer())).
                collect(Collectors.toList()).get(0));

        fillPlayersHands(game, rummikub);
        fillBoard(game, rummikub);
        
        return game;
    }

    private static List<PlayerInfo> createPlayerInfo(generated.Players players) {
        List<PlayerInfo> playersInfo = new ArrayList<PlayerInfo>();
        for (generated.Players.Player player : players.getPlayer()) {
            playersInfo.add(new PlayerInfo(player.getName(), player.getType() == generated.PlayerType.HUMAN, player.isPlacedFirstSequence()));
        }

        return playersInfo;
    }

    private static void fillPlayersHands(RummikubGame game, Rummikub rummikub) {
        List<Player> players = game.getPlayers();
        List<generated.Players.Player> playersToParse = rummikub.getPlayers().getPlayer();
        List<Tile> pileTiles = game.getPile().getTiles();

        int playerIndex = 0;

        for (Player player : players) {
            generated.Players.Player playerToparse = playersToParse.get(playerIndex);
            List<generated.Tile> tilesToParse = playerToparse.getTiles().getTile();

            for (generated.Tile tileToParse : tilesToParse) {
                Tile tileToAdd = removeCompatableGameTileFromPile(game.getPile(), tileToParse);
                player.addTileToHand(tileToAdd);
            }

            playerIndex++;
        }
    }

    private static void fillBoard(RummikubGame game, Rummikub rummikub) {
        List<Sequence> sequences = game.getBoard().getSequences();
        List<generated.Board.Sequence> sequencesToParse = rummikub.getBoard().getSequence();

        int sequenceIndex = 0;

        for (generated.Board.Sequence sequenceToParse : sequencesToParse) {
            Sequence sequence = sequences.get(sequenceIndex);
            List<generated.Tile> tilesToParse = sequenceToParse.getTile();

            for (generated.Tile tileToParse : tilesToParse) {
                Tile tileToAdd = removeCompatableGameTileFromPile(game.getPile(), tileToParse);
                sequence.AddTile(sequence.Size(), tileToAdd);
            }

            sequenceIndex++;
        }
    }

    private static Tile.Color ConverToGameTileColor(generated.Color colorToConvert) {
        Tile.Color color;

        switch (colorToConvert) {
            case BLACK:
                color = Tile.Color.BLACK;
                break;
            case BLUE:
                color = Tile.Color.BLUE;
                break;
            case RED:
                color = Tile.Color.RED;
                break;
            case YELLOW:
                color = Tile.Color.YELLOW;
                break;
            default:
                throw new AssertionError();
        }

        return color;
    }

    private static Tile removeCompatableGameTileFromPile(Pile pile, generated.Tile tileToParse) {
        List<Tile> pileTiles = pile.getTiles();

        Tile.Color color = ConverToGameTileColor(tileToParse.getColor());
        Tile tileRes = pileTiles.stream().filter(tile -> tile.getValue() == tileToParse.getValue() && tile.getColor() == color).
                collect(Collectors.toList()).get(0);
        pileTiles.remove(tileRes);

        return tileRes;
    }

}
