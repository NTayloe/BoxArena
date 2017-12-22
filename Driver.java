package BoxArena;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Driver extends JFrame{
	public Driver(){
		setTitle("BoxArena");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new BoxArena());
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				Driver d = new Driver();
				d.setVisible(true);
			}
		});
	}
}
