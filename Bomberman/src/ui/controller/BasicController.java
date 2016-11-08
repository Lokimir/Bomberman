package ui.controller;

import java.awt.event.KeyEvent;

import core.Bomb;
import core.Cell;
import core.Model;
import core.Player;

public class BasicController extends PlayerController {

	private KeyBoardOptions keyboard;

	public BasicController(Model model, Player player, KeyBoardOptions keyBoard){
		super(model, player);
		this.keyboard = keyBoard;
	}

	@Override
	public void keyPressed(KeyEvent e){
		Cell nextCell = null;
		if(e.getKeyCode() == keyboard.getKey(Action.UP))
			nextCell = model.getMap().getCell(player.getX(), player.getY()-1);
		else if (e.getKeyCode() == keyboard.getKey(Action.DOWN))
			nextCell = model.getMap().getCell(player.getX(), player.getY()+1);
		else if (e.getKeyCode() == keyboard.getKey(Action.LEFT))
			nextCell = model.getMap().getCell(player.getX()-1, player.getY());
		else if (e.getKeyCode() == keyboard.getKey(Action.RIGHT))
			nextCell = model.getMap().getCell(player.getX()+1, player.getY());
		
		if (nextCell != null && nextCell.isBroke()){
			player.move(nextCell.getX(), nextCell.getY());
		}
		
		if(e.getKeyCode() == keyboard.getKey(Action.DROP)){
			if (player.getBombStats().getDroppableBomb() > 0){
				Bomb b = player.dropBomb();
				b.start(player.getX(), player.getY(), player.getBombStats(), gview, model.getBombs());
			}
		}
		
		gview.repaint();
	};
}
