package rummikub.controller;


import generated.Players;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import rummikub.controller.exception.DuplicateNameException;
import rummikub.controller.exception.EmptyNameException;
import rummikub.model.Player;

public class PlayersManager {

//    private final Players playersModel;
//
//    public PlayersManager() {
//	playersModel = new Players();
//    }
//
//    public Player addPlayer(String name, boolean isHuman) throws DuplicateNameException, EmptyNameException {
//	if (name == null || name.isEmpty()){
//	    throw new EmptyNameException();
//	}
//	Player newPlayer = new Player(name, isHuman);
//	if (playersModel.isPlayerExists(newPlayer)) {
//	    throw new DuplicateNameException();
//	} else {
//	    playersModel.addPlayer(newPlayer);
//	}
//	return newPlayer;
//    }
//
//    public Collection<Player> getPlayers(){
//	ArrayList<Player> sortedPlayersList = new ArrayList<>(playersModel.getPlayers());
//	Collections.sort(sortedPlayersList, new PlayerComparator()) ;
//	return sortedPlayersList;
//    }
//
//    static class PlayerComparator implements Comparator<Player> {
//	@Override
//	public int compare(Player o1, Player o2) {
//	    return o1.getName().compareTo(o2.getName());
//	}
//    }
}
