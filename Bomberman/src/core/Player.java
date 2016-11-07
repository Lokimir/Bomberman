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
		if( this.x - 1 >= 0)
			this.x--;			
	}
	
	public void moveRight(){
		if( this.x + 1 <= MapSetup.getInstance().getWidth())
			this.x++;			
	}
	
	public void moveUp(){
		if( this.y - 1 >= 0)
			this.y--;			
	}
	
	public void moveDown(){
		if( this.y + 1 <= MapSetup.getInstance().getHeight())
			this.y++;			
	}
	
	public void dropBomb(){
		bomb.drop(x, y);
	}
}
