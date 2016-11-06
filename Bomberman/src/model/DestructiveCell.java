package model;

public class DestructiveCell extends Cell {

	public DestructiveCell() {
		super();
	}

	@Override
	public void updateState() {
		if(!isDestroyed)
			super.isDestroyed = true;
	}
}
