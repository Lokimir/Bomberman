package model;

public abstract class Cell {

	protected boolean isDestroyed;

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public Cell(){
		isDestroyed = false;
	}
	
	public abstract void destroy();
}
