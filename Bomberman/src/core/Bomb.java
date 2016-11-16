package core;

import java.util.ArrayList;

import ui.view.BasicDraftman;

public class Bomb{
	
	private int x, y;
	private BombStats bombStats;
	private ArrayList<Cell> targets;
	
	private boolean explosing;
	
	public Bomb(int x, int y, BombStats bombStats) {
		this.x = x;
		this.y = y;
		this.bombStats = new BombStats(bombStats);
		this.targets = new ArrayList<Cell>();
		this.explosing = false;
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
	
	public void explode() {
		explosing = true;
	}
	
	public boolean isExplosing(){
		return explosing;
	}

	public ArrayList<Cell> getTargets() {
		return targets;
	}
}