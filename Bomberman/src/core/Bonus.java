package core;

import java.awt.image.BufferedImage;

public abstract class Bonus {
	
	public Bonus(){
	}
	
	public abstract void apply(BombStats bStats);
	
	public abstract BufferedImage getSprite();
}
