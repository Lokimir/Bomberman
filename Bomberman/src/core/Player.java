package core;

public class Player {

	private int x,y;
	private Bomb bomb;

	public Player(int x, int y){
		this.x = x;
		this.y = y;
		this.bomb = new Bomb();
	}

	public int getX() {
		return x;
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

	public void dropBomb(){
		bomb.drop(x, y);
	}
}
