package core;

public class Player {

	private int x,y;
	private BombStats bombStats;

	public Player(int x, int y){
		this.x = x;
		this.y = y;
		this.bombStats = new BombStats();
	}

	public int getX() {
		return x;
	}

	public BombStats getBombStats() {
		return bombStats;
	}

	public int getY() {
		return y;
	}

	public void moveLeft(){
		this.x--;			
	}

	public void moveRight(){
		this.x++;			
	}

	public void moveUp(){
		this.y--;			
	}

	public void moveDown(){
		this.y++;			
	}

	public BombThread dropBomb(){
		return new BombThread();
	}
}
