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
	
	public Bonus getBonus() {
		return this.bonus;
	}
	
	/**return a random bonus in the cell
	 * return null if they have no bonus in the cell
	 * @return Bonus
	 */
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

	public void removeBonus() {
		bonus = null;
	}
	
	public void accept(BombermanVisitor bv) {
		bv.visitCell(this);
	}
}