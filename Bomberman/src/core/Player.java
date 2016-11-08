package core;

import ui.view.BasicDraftman;

public class Player {
	
	private int x,y;
	private BombStats bombStats;

	public Player(int x, int y){
		this.x = x;
		this.y = y;
		this.bombStats = new BombStats();
	}

	public int getX() {
		return x;
	}

	public BombStats getBombStats() {
		return bombStats;
	}

	public int getY() {
		return y;
	}

	public void accept(BasicDraftman bd) {
		bd.visitPlayer(this);
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
