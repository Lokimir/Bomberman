package ui.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ui.GameView;
import core.Model;

public class BasicController implements KeyListener{
	
	protected Model model;
	protected GameView gview;
	
	public BasicController(Model model)
	{
		this.model = model;
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
