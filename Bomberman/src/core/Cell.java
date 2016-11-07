package core;

public class Cell {

	private StateCell state;

	public Cell(StateCell state){
		this.state = state;
	}
	
	public void setState(StateCell state){
		this.state = state;
	}
	
	public void destroy(){
		if(state == StateCell.BREAKABLE)
			state = StateCell.BROKE;
	}

	public boolean isBreakable() {
		return (state == StateCell.BREAKABLE);
	}

	public boolean isBroke() {
		return (state == StateCell.BROKE);
	}
}