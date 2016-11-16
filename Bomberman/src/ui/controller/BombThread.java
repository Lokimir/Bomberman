package ui.controller;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import ui.Bomberman;
import ui.GameView;
import core.Bomb;
import core.BombStats;
import core.Model;
import core.Player;
import core.StateCell;

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
		model.getBombs().add(bomb);
		player.getBombStats().decreaseBomb();
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				explode();
				removeBomb();
				gview.repaint();
			}
		}, bombStats.getDuration()-bombStats.getDuration()/10);

		t.schedule(new TimerTask() {
			@Override
			public void run() {
				explode();
				
				gview.repaint();
			}
		}, bombStats.getDuration());
	}

	public void explode() {	
		BombStats bombStats = bomb.getBombStats();
		explode(bomb.getX(), bomb.getY(), 1);
		explode(bomb.getX() + 1, bomb.getY(), bombStats.getSpread());
		explode(bomb.getX(), bomb.getY() - 1, bombStats.getSpread());
		explode(bomb.getX() - 1, bomb.getY(), bombStats.getSpread());
		explode(bomb.getX(), bomb.getY() + 1, bombStats.getSpread());
		if(model.isEndGame())
			gview.switchEndGameView();
	}

	private void explode(int x, int y, int spread){

		if(spread > 0){
			model.getMap().getCell(x, y).destroy();
			ArrayList<Player> players = new ArrayList<Player>(gview.getModel().getPlayers());
			for(Player p : players)
				if(p.getX() == x && p.getY() == y){
					model.removePlayer(p);
				}
			if(model.getMap().getCell(x, y).getState() != StateCell.UNBREAKABLE)
				if( x - bomb.getX() > 0 )
					explode( x + 1, y, spread - 1 );
				else if( x - bomb.getX() < 0)
					explode( x - 1, y, spread - 1 );
				else if( y - bomb.getY() > 0)
					explode( x, y + 1, spread - 1 );
				else if( y - bomb.getY() < 0)
					explode( x, y - 1 , spread - 1 );	
		}
	}

	public void removeBomb(){
		player.getBombStats().increaseBomb();
		model.getBombs().remove(bomb);
	}
}