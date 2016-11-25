package core.cell;

import java.awt.image.BufferedImage;

import core.bonus.Bonus;
import core.bonus.BonusBomb;
import core.bonus.BonusSpread;
import ui.view.BombermanVisitor;


public abstract class Cell {

	protected int x, y;
	protected Bonus bonus;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
		this.bonus = randomBonus();
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

	private Bonus randomBonus() {
		Bonus bonus;
		double percent = Math.random()*100;
		if(percent < 7.5){
			bonus = new BonusSpread();
		} else if (percent < 15)
			bonus = new BonusBomb();
		else 
			bonus = null;
		return bonus;
	}

	public boolean containBonus() {
		return (bonus != null);
	}

	public Bonus takeBonus() {
		Bonus _bonus = bonus;
		bonus = null;
		return _bonus;
	}
	
	public void accept(BombermanVisitor bv) {
		bv.visitCell(this);
	}

	public Bonus getBonus() {
		return this.bonus;
	}
}