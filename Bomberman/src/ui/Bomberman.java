package ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import core.Model;

public class Bomberman extends JFrame {
	
	Model model;
	GameView gview;
	
	public Bomberman(){
		super("Bomberman");
		
		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		model = Model.getInstance();
		
		this.gview = new GameView(this.model);
		this.gview.setPreferredSize(new Dimension(528,528));
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
