package core.bonus;

import java.awt.image.BufferedImage;

import core.BombStats;

public abstract class Bonus {
	
	public Bonus(){
	}
	
	public abstract void apply(BombStats bStats);
	
	public abstract BufferedImage getSprite();
}
