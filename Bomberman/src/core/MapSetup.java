package core;

public class MapSetup {
	
	private final static int WIDTH = 10;
	private final static int HEIGHT = 10;
	private static MapSetup setup;
	
	private MapSetup(){
	}
	
	public static MapSetup getInstance(){
		if( setup == null )
			setup = new MapSetup();
		return setup;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}
