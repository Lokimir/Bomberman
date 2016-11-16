package ui.controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.Bomb;
import core.BombStats;
import core.Cell;
import core.Model;
import core.Player;
import core.StateCell;
import ui.GameView;

public class BombThread extends Thread {

	private GameView gview;
	private Model model;
	private Player player;
	private Bomb bomb;

	public BombThread(Model model, GameView gview, Player player, Bomb bomb) {
		this.player = player;
		this.bomb = bomb;
		this.model = model;
		this.gview = gview;
	}

	@Override
	public void run() {
		BombStats bombStats = bomb.getBombStats();
		
		// Selection des cibles
		this.getTarget(bomb.getX(), bomb.getY(), bomb.getBombStats().getSpread());
		
		model.getBombs().add(bomb);
		player.getBombStats().decreaseBomb();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				explode();
				gview.repaint();
			}
		}, bombStats.getDuration()-bombStats.getDuration()/10);

		t.schedule(new TimerTask() {
			@Override
			public void run() {
				removeBomb();
				gview.repaint();
			}
		}, bombStats.getDuration());
	}

	
	public void explode() {			
		for (Cell c : bomb.getTargets()){
			ArrayList<Player> players = new ArrayList<Player>(gview.getModel().getPlayers());
			for(Player p : players)
				if(p.getX() == c.getX() && p.getY() == c.getY()){
					gview.getModel().removePlayer(p);
					gview.removeKeyListener(gview.getController(p));
					gview.getControllers().remove(p);
				}
			
			c.destroy();
		}
				
		bomb.explode();
	}

	private void getTarget(int x, int y, int spread){
		if(spread >= 0){
			Cell c = model.getMap().getCell(x, y);
			if (c.getState() != StateCell.UNBREAKABLE){
				bomb.getTargets().add(c);
	
				if( x - bomb.getX() > 0 )
					getTarget( x + 1, y, spread - 1 );
				else if( x - bomb.getX() < 0)
					getTarget( x - 1, y, spread - 1 );
				else if( y - bomb.getY() > 0)
					getTarget( x, y + 1, spread - 1 );
				else if( y - bomb.getY() < 0)
					getTarget( x, y - 1 , spread - 1 );
				else{
					getTarget(x+1, y, spread - 1);
					getTarget(x-1, y, spread - 1);
					getTarget(x, y+1, spread - 1);
					getTarget(x, y-1, spread - 1);
				}
			}
		}
	}

	public void removeBomb(){
		player.getBombStats().increaseBomb();
		model.getBombs().remove(bomb);
	}
}