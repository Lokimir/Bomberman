package ui.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Model;
import core.Player;
import ui.GameView;

public class PlayerController implements KeyListener{
	
	protected Model model;
	protected GameView gview;
	protected Player player;
	
	public PlayerController(Model model, Player player)
	{
		this.model = model;
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
	}

	@Override
	public void keyReleased(KeyEvent e){
	}

	@Override
	public void keyTyped(KeyEvent e){

	}

	public void setView(GameView gview) {
		this.gview = gview;
	};
}
