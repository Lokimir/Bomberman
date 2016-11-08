package core;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ui.GameView;
import ui.view.BasicDraftman;

public class Bomb extends Thread {
	
	private BombStats bombStats;
	private int x, y;
	
	private ArrayList<Bomb> bombs;
	private GameView gview;
	
	public Bomb() {
	}

	public void start(int x, int y, BombStats bombStats, GameView gview, ArrayList<Bomb> bombs){
		this.bombStats = bombStats;
		this.gview = gview;
		this.x = x;
		this.y = y;
		this.bombs = bombs;
		
		bombStats.decreaseBomb();
		bombs.add(this);
		this.start();
	}
	
	@Override
	public void run() {
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
		explode(x, y, 1);
		explode(x + 1, y, bombStats.getSpread());
		explode(x, y - 1, bombStats.getSpread());
		explode(x - 1, y, bombStats.getSpread());
		explode(x, y + 1, bombStats.getSpread());		
	}

	private void explode(int x, int y, int spread){

		if(spread > 0 && x >= 0 && x <= Map.getInstance().getWidth() && y >= 0 && y <= Map.getInstance().getHeight()){
			Map.getInstance().getCell(x, y).destroy();interrupt();
			for(Player p : gview.getModel().getPlayers())
				if(p.getX() == x && p.getY() == y){
					gview.getModel().removePlayer(p);
					gview.removeKeyListener(gview.getController(p));
				}
			if(Map.getInstance().getCell(x, y).isBreakable())
				if( x - this.x 	> 0 )
					explode( x + 1, y, spread - 1 );
				else if( x - this.x < 0)
					explode( x - 1, y, spread - 1 );
				else if( y - this.y > 0)
					explode( x, y + 1, spread - 1 );
				else if( y - this.y < 0)
					explode( x, y - 1 , spread - 1 );
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void removeBomb(){
		bombs.remove(this);
		bombStats.increaseBomb();
	}

	public void accept(BasicDraftman bd) {
		bd.visitBomb(this);
	}	
}