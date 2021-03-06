package ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ui.GameView;
import ui.view.Sprite;
import core.Bomb;
import core.BombStats;
import core.Model;
import core.Player;
import core.cell.Cell;
import core.cell.FloorCell;
import core.cell.UnbreakableCell;

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
		
		// Select targets
		this.getTarget(bomb.getX(), bomb.getY(), bomb.getBombStats().getSpread());
		
		try {
			bomb.setCurrentSprite(new Sprite(bomb.getBombSprite(), bombStats.getDuration() - bombStats.getDuration()/10, bomb.getX(), bomb.getY()));
			bomb.getCurrentSprite().setView(gview);
			new Thread(bomb.getCurrentSprite()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			ArrayList<Player> players = new ArrayList<Player>(model.getPlayers());
			for(Player p : players)
				if(p.getX() == c.getX() && p.getY() == c.getY()){
					gview.getModel().removePlayer(p);
				}
			if(model.isEndGame())
				gview.switchEndGameView();
			destroy(c);
		}
				
		bomb.explode();
	}

	private void destroy(Cell cell) {
		Cell nextCell = cell.nextState();
		
		if(model.getMap().getCells().remove(cell))
			model.getMap().getCells().add(nextCell);
	}

	private void getTarget(int x, int y, int spread){
		if(spread >= 0){
			Cell c = model.getMap().getCell(x, y);
			if (c.getClass() != UnbreakableCell.class){
				bomb.getTargets().add(c);
			}
			if (c.getClass() == FloorCell.class){
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