package ui.controller;

import java.awt.event.KeyEvent;

import core.Model;
import core.Player;
import ui.GameView;

public class BasicController extends PlayerController {

	private Model model;
	private Player player;
	private GameView gview;
	
	public BasicController(Model model, Player player){
		super(model, player);
	}

	@Override
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_Z:
			player.moveUp();
			break;
		case KeyEvent.VK_S:
			player.moveDown();
			break;
		case KeyEvent.VK_Q:
			player.moveLeft();
			break;
		case KeyEvent.VK_D:
			player.moveRight();
			break;
		};
		
		this.gview.repaint();
	};

}