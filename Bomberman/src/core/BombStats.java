package core;

public class BombStats {
	
	private long duration;
	private int spread;	
	private int droppableBomb;
	
	public BombStats(int duration, int spread, int droppableBomb){
		this.duration = duration;
		this.spread = spread;
		this.droppableBomb = droppableBomb;
		
	}
	
	public BombStats(){
		this.duration = 1500;
		this.spread = 1;
		this.droppableBomb = 1;
	}
	
	public BombStats(BombStats bombStats) {
		this.duration = bombStats.duration;
		this.spread = bombStats.spread;
		this.droppableBomb = bombStats.droppableBomb;
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

	public void increaseSpread() {
		spread++;
	}	
}
