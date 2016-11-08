package core;

import java.util.ArrayList;

public class Model {
	
	private Map map;
	private ArrayList<Player> players;
	private static Model model;
	private volatile ArrayList<Bomb> bombs;
	
	private Model(){
		bombs = new ArrayList<Bomb>();
		map = Map.getInstance();
		players = new ArrayList<Player>(); 
		players.add(new Player(1, 1));
		players.add(new Player(map.getWidth()-1, map.getHeight()-1));
	}
	
	public static Model getInstance(){
		if(model == null)
			model = new Model();
		return model;
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
		players.remove((Player) player);
	}

}
