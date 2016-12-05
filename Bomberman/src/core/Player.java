package core;

import java.awt.image.BufferedImage;

import ui.view.BombermanVisitor;

public class Player {
	
	private BufferedImage playerSprite;
	
	private final int INIT_X_VALUE;
	private final int INIT_Y_VALUE;
	
	private int x,y;
	private BombStats bombStats;
	private boolean isAlive;

	public Player(int x, int y){
		INIT_X_VALUE = x;
		INIT_Y_VALUE = y;
		this.x = x;
		this.y = y;
		this.bombStats = new BombStats();
		this.isAlive = true;
	}

	public boolean isAlive() {
		return isAlive;
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

	public void accept(BombermanVisitor bv) {
		bv.visitPlayer(this);
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void restorePlayer() {
		this.x = INIT_X_VALUE;
		this.y = INIT_Y_VALUE;
		this.bombStats = new BombStats();
		this.isAlive = true;
	}

	public void die() {
		isAlive = false;
	}

	public BufferedImage getSprite() {
		return playerSprite;
	}

	public void setSprite(BufferedImage playerSprite) {
		this.playerSprite = playerSprite;
	}
}
