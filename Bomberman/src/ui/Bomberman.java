package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Model;

public class Bomberman extends JFrame {
	
	Model model;
	JPanel gview;
	
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
		this.gview.setPreferredSize(new Dimension(model.getMap().getWidth()*48+48,model.getMap().getHeight()*48+48));
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);
	}
	

	public void setGview(JPanel gview) {
		this.gview = gview;
	}

	
	public static void main(String[] args) {
		Bomberman game = new Bomberman();
		game.setResizable(false);
		game.pack();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}
}
