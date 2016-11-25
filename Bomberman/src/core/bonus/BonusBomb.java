package core.bonus;

import java.awt.image.BufferedImage;

import core.BombStats;

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
