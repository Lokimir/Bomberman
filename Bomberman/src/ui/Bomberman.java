package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Model;

public class Bomberman extends JFrame {
	
	public final static String GAMEVIEW = "Gameview";
	public final static String ENDGAMEVIEW = "EndGameview";
	public static final String STARTVIEW = "Startview";
	
	private Model model;
	private JPanel gview;
	
	public Bomberman(){
		super("Bomberman");
		
		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		model = new Model();
		CardLayout cd = new CardLayout();
		this.gview = new JPanel(cd);
		this.gview.add(new GameView(model,this.gview), GAMEVIEW);
		this.gview.add(new EndGameView(model, this.gview), ENDGAMEVIEW);
		this.gview.add(new StartView(model, this.gview), STARTVIEW);
		cd.show(this.gview, STARTVIEW);
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Bomberman game = new Bomberman();
		game.setResizable(false);
		game.pack();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}
}
