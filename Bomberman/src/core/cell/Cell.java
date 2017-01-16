package core.cell;

import java.awt.image.BufferedImage;

import core.bonus.Bonus;
import ui.view.BombermanVisitor;


public abstract class Cell {

	protected int x, y;
	protected Bonus bonus;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Cell(Cell cell){
		this.x = cell.x;
		this.y = cell.y;
		this.bonus = cell.bonus;
	}
	
	public abstract boolean isWalkable();
	public abstract Cell nextState();
	public abstract BufferedImage getSprite();
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Bonus getBonus() {
		return this.bonus;
	}

	public boolean containBonus() {
		return (bonus != null);
	}

	public void removeBonus() {
		bonus = null;
	}
	
	public void accept(BombermanVisitor bv) {
		bv.visitCell(this);
	}
}