package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import core.Model;
import core.Player;
import ui.controller.BasicController;
import ui.controller.PlayerController;
import core.BombThread;
import core.Cell;
import core.DestructiveCell;

public class GameView extends JPanel {

	private Model model;
	private ArrayList<BasicController> controller;
	
	public GameView(Model model)
	{
		this.model = model;
		this.controller = new ArrayList<BasicController>();
		this.controller.add(new BasicController(model, model.getPlayer(0), new KeyBoardOptions(KeyEvent.VK_Z,KeyEvent.VK_S,KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_SPACE)));
		this.controller.add(new BasicController(model, model.getPlayer(1), new KeyBoardOptions(KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER)));
		this.controller.get(0).setView(this);
		this.controller.get(1).setView(this);
		for(BasicController bc : controller){
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
		for (BombThread b : model.getBombs()){
			b.paintComponent(g2d);			
		}
		
		
		for(Player p : model.getPlayers()){
			g2d.setColor(Color.RED);
			g2d.fillOval(p.getX()*64, p.getY()*48, 64, 48);			
		}
	}
}
