package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import ui.controller.BasicController;
import ui.controller.KeyBoardOptions;
import ui.controller.PlayerController;
import ui.view.BasicDraftman;
import core.Bomb;
import core.BombThread;
import core.Model;
import core.Player;

public class GameView extends JPanel {

	private Model model;
	private Map<Player,BasicController> controllers;
	
	public GameView(Model model)
	{
		this.model = model;
		this.controllers = new HashMap<>();
		this.controllers.put(model.getPlayer(0),new BasicController(model, model.getPlayer(0), new KeyBoardOptions(KeyEvent.VK_Z,KeyEvent.VK_S,KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_SPACE)));
		this.controllers.put(model.getPlayer(1),new BasicController(model, model.getPlayer(1), new KeyBoardOptions(KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER)));
		this.controllers.get(model.getPlayer(0)).setView(this);
		this.controllers.get(model.getPlayer(1)).setView(this);
		this.addKeyListener(this.controllers.get(model.getPlayer(0)));
		this.addKeyListener(this.controllers.get(model.getPlayer(1)));
		
		this.setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		BasicDraftman bd = new BasicDraftman();
		bd.setGraphics(g2d);
		
		this.model.getMap().accept(bd);
		
		for (Bomb b : this.model.getBombs())
		{
			b.accept(bd);
		}
		
		for (Player p : this.model.getPlayers())
		{
			p.accept(bd);
		}
	}
	
	public Model getModel(){
		return model;
	}
	
	public PlayerController getController(Player p){
		return controllers.get(p);
	}

	public Map<Player,BasicController> getControllers() {
		return controllers;
	}
}
