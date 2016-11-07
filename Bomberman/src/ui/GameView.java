package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import core.BombThread;
import core.Cell;
import core.DestructiveCell;
import core.Model;
import core.Player;
import ui.controller.BasicController;
import ui.controller.KeyBoardOptions;

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
		
		int i = 0;
		for (ArrayList<Cell> lc : model.getMap().getCells()){
			int j = 0;
			for (Cell c : lc){
				if (c.getClass() == DestructiveCell.class){
					if(c.isDestroyed())
						g2d.setColor(Color.WHITE);
					else
						g2d.setColor(Color.gray);
				}					
				else	
					g2d.setColor(Color.BLACK);
				
					g2d.fillRect(i*64, j*48, 64, 48);
				
				j++;
			}
			i++;
		}
		
		for(Player p : model.getPlayers()){
			g2d.setColor(Color.RED);
			g2d.fillOval(p.getX()*64, p.getY()*48, 64, 48);			
		}
			
		g2d.setColor(Color.CYAN);
		for (BombThread b : model.getBombs()){
			b.paintComponent(g2d);			
		}
	}
}
