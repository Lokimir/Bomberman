package ui.controller;

import java.awt.event.KeyEvent;

import core.Map;
import core.MapSetup;
import core.Model;
import core.Player;
import ui.GameView;

public class BasicController extends PlayerController {
	
	public BasicController(Model model, Player player){
		super(model, player);
	}

	@Override
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_Z:
			if(model.getPlayer().getY()-1 >= 0 && model.getMap().getCell(model.getPlayer().getX(), model.getPlayer().getY() -1).isDestroyed())
				player.moveUp();
			break;
		case KeyEvent.VK_S:
			if(model.getPlayer().getY()+1 <= MapSetup.getInstance().getHeight() && model.getMap().getCell(model.getPlayer().getX(), model.getPlayer().getY() +1).isDestroyed())
				player.moveDown();
			break;
		case KeyEvent.VK_Q:
			if(model.getPlayer().getX()-1 >= 0 && model.getMap().getCell(model.getPlayer().getX() -1, model.getPlayer().getY()).isDestroyed())
				player.moveLeft();
			break;
		case KeyEvent.VK_D:
			if(model.getPlayer().getX()+1 <= MapSetup.getInstance().getWidth() && model.getMap().getCell(model.getPlayer().getX() +1, model.getPlayer().getY()).isDestroyed())
			player.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			player.dropBomb();
			break;
		};
		
		gview.repaint();
	};

}
