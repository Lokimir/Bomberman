package core.cell;

import java.awt.image.BufferedImage;

public class FloorCell extends Cell {
	
	private static BufferedImage sprite;

	public FloorCell(int x, int y) {
		super(x, y);
		bonus = null;
	}
	
	public FloorCell(Cell cell) {
		super(cell);
	}

	public BufferedImage getSprite() {
		return sprite;
	}
	
	public static void setSprite(BufferedImage sprite){
		FloorCell.sprite = sprite;
	}

	@Override
	public Cell nextState() {
		return this;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}
}
