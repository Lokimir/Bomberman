package core;

import java.lang.Thread.State;

public class Bomb {
	
	private long duration;
	private int spread;
	
	private int x, y;
	
	private boolean isDrop;
	private BombThread bThread;

	public Bomb(int duration, int spread){
		isDrop = false;
		this.duration = duration;
		this.spread = spread;
		bThread = new BombThread(this);
	}
	
	public Bomb(){
		isDrop = false;
		this.duration = 2000;
		this.spread = 1;
		bThread = new BombThread(this);
	}
	
	public boolean isDrop() {
		return isDrop;
	}

	public void setSpread(int spread) {
		this.spread = spread;
	}

	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public void drop(int x, int y) {
		if( !isDrop ){
			isDrop = true;
			this.x = x;
			this.y = y;
			if(bThread.getState() != State.NEW)
				bThread = new BombThread(this);
			bThread.start();
		}
	}

	public void explode() {
		isDrop = false;
		
		explode(x, y, 1);
		explode(x + 1, y, spread);
		explode(x, y - 1, spread);
		explode(x - 1, y, spread);
		explode(x, y + 1, spread);
	}

	private void explode(int x, int y, int spread){
		if(spread > 0 && x >= 0 && x <= MapSetup.getInstance().getWidth() && y >= 0 && y <= MapSetup.getInstance().getHeight()){
			Map.getInstance().getCell(x, y).destroy();
			if(Map.getInstance().getCell(x, y).getClass() == DestructiveCell.class)
				if( x - this.x > 0 )
					explode( x + 1, y, spread - 1 );
				else if( x - this.x < 0)
					explode( x - 1, y, spread - 1 );
				else if( y - this.y > 0)
					explode( x, y + 1, spread - 1 );
				else if( y - this.y < 0)
					explode( x, y - 1 , spread - 1 );
		}
	}
}
