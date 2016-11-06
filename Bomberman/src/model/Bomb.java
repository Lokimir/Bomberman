package model;

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
			bThread.start();		
		}
	}

	public void explode() {
		
		isDrop = false;
	}

}
