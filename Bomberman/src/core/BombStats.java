package core;

public class BombStats {
	
	private volatile long duration;
	private volatile int spread;	
	private volatile int droppableBomb;
	
	public BombStats(int duration, int spread, int droppableBomb){
		this.duration = duration;
		this.spread = spread;
		this.droppableBomb = droppableBomb;
		
	}
	
	public BombStats(){
		this.duration = 2000;
		this.spread = 1;
		this.droppableBomb = 2;
	}
	
	public int getSpread(){
		return spread;
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

	public int getDroppableBomb() {
		return droppableBomb;
	}

	public void increaseBomb() {
		droppableBomb++;
	}
	
	public void decreaseBomb() {
		droppableBomb--;
	}
	
	
}
