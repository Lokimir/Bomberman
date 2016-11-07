package core;

import java.util.ArrayList;

public class Model {
	
	private Map map;
	private ArrayList<Player> players;
	private static Model model;
	
	private Model(){
		map = Map.getInstance();
		players = new ArrayList<Player>(); 
		players.add(new Player(0, 0));
		players.add(new Player(10, 10));
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
	
	
}
