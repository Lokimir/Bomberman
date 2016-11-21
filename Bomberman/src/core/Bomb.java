package core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ui.view.BasicDraftman;
import ui.view.BombermanVisitor;
import ui.view.Sprite;

public class Bomb{
	
	private static BufferedImage explosionSprite;
	private static BufferedImage bombSprite;

	private int x, y;
	private BombStats bombStats;
	private ArrayList<Cell> targets;
	private boolean explosing;
	
	private Sprite currentSprite;
	
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

    public Sprite getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}

	public void accept(BombermanVisitor bv) {
		bv.visitBomb(this);
	}
	
	public BufferedImage getExplosionSprite() {
		return explosionSprite;
	}

	public static void setExplosionSprite(BufferedImage explosionSprite) {
		Bomb.explosionSprite = explosionSprite;
	}

	public BufferedImage getBombSprite() {
		return bombSprite;
	}

	public static void setBombSprite(BufferedImage bombSprite) {
		Bomb.bombSprite = bombSprite;
	}

}