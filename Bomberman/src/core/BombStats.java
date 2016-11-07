package core;

public class BombStats {
	
	private long duration;
	private int spread;	

	public BombStats(int duration, int spread){
		this.duration = duration;
		this.spread = spread;
	}
	
	public BombStats(){
		this.duration = 2000;
		this.spread = 1;
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
}
