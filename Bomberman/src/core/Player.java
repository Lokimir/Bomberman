package core;

import ui.view.BasicDraftman;

public class Player {
	
	private boolean isAlive;
	private int x,y;
	private BombStats bombStats;

	public Player(int x, int y){
		this.isAlive = true;
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

	public Bomb dropBomb(){
		return new Bomb();
	}

	public void accept(BasicDraftman bd) {
		bd.visitPlayer(this);
	}

	public void die() {
		isAlive = false;
	}

	public boolean isALive() {
		return isAlive;
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
