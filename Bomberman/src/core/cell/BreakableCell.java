package core.cell;

import java.awt.image.BufferedImage;

import core.bonus.Bonus;

public class BreakableCell extends Cell {
	
	private static BufferedImage sprite;
	
	public BreakableCell(int x, int y, Bonus bonus) {
		super(x, y);
		this.bonus = bonus;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public static void setSprite(BufferedImage sprite){
		BreakableCell.sprite = sprite;
	}

	@Override
	public Cell nextState() {
		return new FloorCell(this);
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
}
