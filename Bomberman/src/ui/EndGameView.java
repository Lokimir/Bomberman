package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import ui.controller.MenuController;
import core.Model;

public class EndGameView extends JPanel {
	
	private Model model;
	private MenuController controller;
	
	public EndGameView(Model model, MenuController controller){
		this.model = model;
		this.controller = controller;
		this.addKeyListener(controller);
		this.setPreferredSize(new Dimension(model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48));
		
		this.setFocusable(true);
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48);
	}
}
