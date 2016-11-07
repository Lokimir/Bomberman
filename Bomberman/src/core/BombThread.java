package core;

public class BombThread extends Thread {
	
	private Bomb bomb;
	
	public BombThread(Bomb bomb) {
		this.bomb = bomb;
	}
	
	@Override
	public void run() {
		try {
			//mettre a jour le model de la map (affichage de la bomb sur la map) a faire ici
			Thread.sleep(bomb.getDuration());
			bomb.explode();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
