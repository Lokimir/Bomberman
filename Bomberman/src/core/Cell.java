package core;

public class Cell {

	private StateCell state;
	private Bonus bonus;
	private int x, y;

	public Cell(int x, int y, StateCell state){
		this.state = state;
		this.x = x;
		this.y = y;
		this.bonus = randomBonus();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private Bonus randomBonus() {
		Bonus bonus;
		double percent = Math.random()*100;
		if(percent < 7.5){
			bonus = new BonusSpread();
		} else if (percent < 15)
			bonus = new BonusBomb();
		else 
			bonus = null;
		return bonus;
	}

	public void setState(StateCell state){
		this.state = state;
	}
	
	public void destroy(){
		if(state == StateCell.BREAKABLE)
			state = StateCell.EXPLOSING;
		else if (state == StateCell.EXPLOSING)
			state = StateCell.BROKE;
	}

	public boolean isBreakable() {
		return (state == StateCell.BREAKABLE);
	}

	public boolean isBroke() {
		return (state == StateCell.BROKE);
	}

	public boolean isExplosing() {
		return (state == StateCell.EXPLOSING);
	}
}