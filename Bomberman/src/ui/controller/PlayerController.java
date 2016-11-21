package ui.controller;

import java.awt.event.KeyEvent;

import core.Bomb;
import core.BombStats;
import core.Cell;
import core.Model;
import core.Player;
import core.StateCell;

public class PlayerController extends BasicController {

	private KeyBoardOptions keyboard;
	protected Player player;

	public PlayerController(Model model, Player player, KeyBoardOptions keyBoard){
		super(model);
		this.player = player;
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

		if (nextCell != null && nextCell.getState() == StateCell.BROKE && isAloneOnNextCell(nextCell)){
			player.move(nextCell.getX(), nextCell.getY());

			if (nextCell.containBonus()){
				nextCell.getBonus().apply(player.getBombStats());
				nextCell.takeBonus();
			}
		}

		if(e.getKeyCode() == keyboard.getKey(Action.DROP)){
			if (player.getBombStats().getDroppableBomb() > 0){
				Bomb bomb = new Bomb(player.getX(), player.getY(), new BombStats(player.getBombStats()));
				for(Bomb b : model.getBombs()){
					if(b.getX() == bomb.getX() && b.getY() == bomb.getY()){
						return;
					}
				}
				BombThread thread = new BombThread(model, gview, player, bomb);
				thread.start();						
			}
		}

		gview.repaint();
	}

	private boolean isAloneOnNextCell(Cell cell) {
		for(Player p : model.getPlayers())
			if(p.getX() == cell.getX() && p.getY() == cell.getY())
				return false;
		for(Bomb b : model.getBombs())
			if(b.getX() == cell.getX() && b.getY() == cell.getY())
				return false;
		return true;
	};
}
