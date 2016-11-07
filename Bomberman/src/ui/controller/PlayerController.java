package ui.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Model;
import core.Player;
import ui.GameView;

public class PlayerController implements KeyListener{
	
	private Model model;
	private GameView gview;
	private Player player;
	
	public PlayerController(Model model, Player player)
	{
		this.model = model;
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		System.out.println("lol");
	};

	@Override
	public void keyReleased(KeyEvent e){

		System.out.println("lol");
	};

	@Override
	public void keyTyped(KeyEvent e){

		System.out.println("lol");
	}

	public void setView(GameView gview) {
		this.gview = gview;
	};
}
