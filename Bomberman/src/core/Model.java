package core;

public class Model {
	
	private Map map;
	private Player player;
	private static Model model;
	
	private Model(){
		map = Map.getInstance();
		player = new Player(0, 0);
	}
	
	public static Model getInstance(){
		if(model == null)
			model = new Model();
		return model;
	}
	
	public Map getMap() {
		return map;
	}
	public Player getPlayer() {
		return player;
	}
	
	
}
