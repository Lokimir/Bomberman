package core;

public class BonusBomb extends Bonus {
	
	public BonusBomb() {
		super();
	}

	@Override
	public void apply(BombStats bStats) {
		bStats.increaseBomb();
	}

}
