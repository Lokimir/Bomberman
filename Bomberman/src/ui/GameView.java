package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import core.Bomb;
import core.Cell;
import core.Model;
import core.Player;
import ui.controller.BasicController;
import ui.controller.KeyBoardOptions;
import ui.view.BasicDraftman;

public class GameView extends JPanel {

	private Model model;
	private ArrayList<BasicController> controllers;
	
	public GameView(Model model)
	{
		this.model = model;
		this.controllers = new ArrayList<BasicController>();
		this.controllers.add(new BasicController(model, model.getPlayer(0), new KeyBoardOptions(KeyEvent.VK_Z,KeyEvent.VK_S,KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_SPACE)));
		this.controllers.add(new BasicController(model, model.getPlayer(1), new KeyBoardOptions(KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER)));
		this.controllers.get(0).setView(this);
		this.controllers.get(1).setView(this);
		for(BasicController bc : controllers){
			this.addKeyListener(bc);
		}
		
		this.setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		BasicDraftman bd = new BasicDraftman();
		bd.setGraphics(g2d);
		
		this.model.getInstance().getMap().accept(bd);
		
		for (Bomb b : this.model.getBombs())
		{
			b.accept(bd);
		}
		
		for (Player p : this.model.getPlayers())
		{
			p.accept(bd);
		}
	}
}
