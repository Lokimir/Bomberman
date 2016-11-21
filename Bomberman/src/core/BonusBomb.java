package core;

import java.awt.image.BufferedImage;

public class BonusBomb extends Bonus {
	
	private static BufferedImage sprite;
	
	public BonusBomb() {
		super();
	}

	@Override
	public void apply(BombStats bStats) {
		bStats.increaseBomb();
	}

	public BufferedImage getSprite() {
		return sprite;
	}
	
	public static void setSprite(BufferedImage sprite){
		BonusBomb.sprite = sprite;
	}

}
