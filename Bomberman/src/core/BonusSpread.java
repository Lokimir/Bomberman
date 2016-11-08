package core;

public class BonusSpread extends Bonus {

	public BonusSpread() {
		super();
	}

	@Override
	public void apply(BombStats bStats) {
		bStats.increaseSpread();
	}

}