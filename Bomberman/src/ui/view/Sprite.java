package ui.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import core.BombStats;
import ui.GameView;

public class Sprite implements Runnable{

	private int x, y;
	private long duration;
	
	private BufferedImage sheet;
	private BufferedImage current;
	
	private GameView view;
	
	public Sprite(BufferedImage sheet, long duration, int x, int y) throws IOException{
		this.sheet = sheet;
		this.duration = duration;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run() {
		Timer t = new Timer();
		t.schedule(new TimerTask(){
			int i = 0;
			public void run () {
				if (i > 4){
					t.cancel();
				}
				else {
					current = sheet.getSubimage(i*48, 0, 48, 48);
					i++;
				}
				view.repaint();
			}
			
		}, 0, duration/5);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getCurrent() {
		return current;
	}
	
	public Sprite getSprite(){
		return this;
	}
	public GameView getView() {
		return view;
	}
	
	
	public void setView(GameView view) {
		this.view = view;
	}
}