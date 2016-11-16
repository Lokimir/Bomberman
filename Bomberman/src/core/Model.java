package core;

import java.util.ArrayList;

public class Model {

	private Map map;
	private ArrayList<Player> players;
	private volatile ArrayList<Bomb> bombs;

	public Model(){
		bombs = new ArrayList<>();
		map = new Map();
		players = new ArrayList<Player>(); 
		players.add(new Player(1, 1));
		players.add(new Player(map.getWidth()-1, map.getHeight()-1));
	}

	public Map getMap() {
		return map;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getPlayer(int index) {
		return players.get(index);
	}

	public ArrayList<Bomb> getBombs(){
		return bombs;
	}

	public void removePlayer(Player player) {
		player.die();
	}

	public boolean isEndGame() {
		int alive = 0;
		for(Player p : players)
			if(p.isAlive())
				alive++;
		return (alive < 2);
	}

	public void restore() {
		bombs = new ArrayList<>();
		map = new Map();
		for(Player p : players)
			p.restore();
	}
}
