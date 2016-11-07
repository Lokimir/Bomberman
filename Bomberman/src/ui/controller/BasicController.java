package ui.controller;

import java.awt.event.KeyEvent;

import core.BombThread;
import core.Map;
import core.MapSetup;
import core.Model;
import core.Player;
import ui.controller.KeyBoardOptions;

public class BasicController extends PlayerController {

	private KeyBoardOptions keyboard;

	public BasicController(Model model, Player player, KeyBoardOptions keyBoard){
		super(model, player);
		this.keyboard = keyBoard;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == keyboard.getKey(Action.UP)){
			if(player.getY()-1 >= 0 && model.getMap().getCell(player.getX(), player.getY() -1).isDestroyed())
				player.moveUp();
		}
		else if(e.getKeyCode() == keyboard.getKey(Action.DOWN)){
			if(player.getY()+1 <= MapSetup.getInstance().getHeight() && model.getMap().getCell(player.getX(), player.getY() +1).isDestroyed())
				player.moveDown();
		}
		else if(e.getKeyCode() == keyboard.getKey(Action.LEFT)){
			if(player.getX()-1 >= 0 && model.getMap().getCell(player.getX() -1, player.getY()).isDestroyed())
				player.moveLeft();
		}
		else if(e.getKeyCode() == keyboard.getKey(Action.RIGHT)){
			if(player.getX()+1 <= MapSetup.getInstance().getWidth() && model.getMap().getCell(player.getX() +1, player.getY()).isDestroyed())
				player.moveRight();
		}
		else if(e.getKeyCode() == keyboard.getKey(Action.DROP)){
			BombThread b = player.dropBomb();
			b.start(player.getX(), player.getY(), player.getBombStats(), gview, model.getBombs());
			model.getBombs().add(b);
		}
		gview.repaint();
	};
}
