package core;

import ui.view.BasicDraftman;

public class Bomb{
	
	private int x, y;
	private BombStats bombStats;
	
	public Bomb(int x, int y, BombStats bombStats) {
		this.bombStats = new BombStats(bombStats);
		this.x = x;
		this.y = y;
	}
	
	public BombStats getBombStats() {
		return bombStats;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void accept(BasicDraftman bd) {
		bd.visitBomb(this);
	}	
}