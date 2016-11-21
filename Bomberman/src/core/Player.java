package core;

import java.awt.image.BufferedImage;

import ui.view.BombermanVisitor;

public class Player {
	
	public static BufferedImage playerSprite;

	public boolean isAlive() {
		return isAlive;
	}

	private final int INITXVALUE;
	private final int INITYVALUE;
	private int x,y;
	private BombStats bombStats;
	private boolean isAlive;

	public Player(int x, int y){
		INITXVALUE = x;
		INITYVALUE = y;
		this.x = x;
		this.y = y;
		this.bombStats = new BombStats();
		this.isAlive = true;
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


	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void restore() {
		this.x = INITXVALUE;
		this.y = INITYVALUE;
		this.bombStats = new BombStats();
		this.isAlive = true;
	}

	public void die() {
		isAlive = false;
	}
	
	public void accept(BombermanVisitor bv) {
		bv.visitPlayer(this);
	}
}
