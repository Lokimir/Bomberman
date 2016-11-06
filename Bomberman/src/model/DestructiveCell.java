package model;

public class DestructiveCell extends Cell {

	public DestructiveCell() {
		super();
	}

	@Override
	public void destroy() {
		if(!isDestroyed)
			super.isDestroyed = true;
	}
}
