package core.bonus;

import java.awt.image.BufferedImage;

import core.BombStats;

public class BonusSpread extends Bonus {

	private static BufferedImage sprite;
	
	public BonusSpread() {
		super();
	}

	@Override
	public void apply(BombStats bStats) {
		bStats.increaseSpread();
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public static void setSprite(BufferedImage sprite) {
		BonusSpread.sprite = sprite;
	}

}