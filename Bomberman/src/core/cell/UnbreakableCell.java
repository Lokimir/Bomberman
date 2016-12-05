package core.cell;

import java.awt.image.BufferedImage;

public class UnbreakableCell extends Cell {

	private static BufferedImage sprite;
	
	public UnbreakableCell(int x, int y) {
		super(x, y);
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public static void setSprite(BufferedImage sprite){
		UnbreakableCell.sprite = sprite;
	}

	@Override
	public Cell nextState() {
		return this;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

}
