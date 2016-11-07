package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import core.Cell;
import core.DestructiveCell;
import core.Model;
import ui.controller.BasicController;
import ui.controller.PlayerController;

public class GameView extends JPanel {

	private Model model;
	private BasicController controller;
	
	public GameView(Model model)
	{
		this.model = model;
		this.controller = new BasicController(model, model.getPlayer());
		this.controller.setView(this);
		this.addKeyListener(controller);

		this.setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		int i = 0;
		for (ArrayList<Cell> lc : model.getMap().getCells()){
			int j = 0;
			for (Cell c : lc){
				if (c.getClass() == DestructiveCell.class)
					g2d.setColor(Color.BLACK);
				else
					g2d.setColor(Color.WHITE);
				
					g2d.fillRect(i*64, j*48, 64, 48);
				
				j++;
			}
			i++;
		}
		
		g2d.setColor(Color.RED);
		g2d.fillOval(model.getPlayer().getX()*64, model.getPlayer().getY()*48, 64, 48);
	
		g2d.setColor(Color.CYAN);
	}
}
