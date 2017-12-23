package BoxArena;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class BoxArena extends JPanel implements Runnable, ActionListener{
	private final int DELAY = 25;
	private double PLAYER_WIDTH = 200;
	private double PLAYER_HEIGHT = 200;
	private double PLAYER_MOVEMENT_STEP = 20;
	
	private Thread animator;
	
	private double x = 100;
	private double y = 100;

	private boolean moveRight = false;
	private boolean moveLeft = false;
	private boolean moveUp = false;
	private boolean moveDown = false;
	
	public BoxArena (){
		addKeyListener(new KeyListener(){
			
			@Override
			public void keyTyped(KeyEvent e){
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e){
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_D){moveRight = false;}
				if(key == KeyEvent.VK_A){moveLeft = false;}
				if(key == KeyEvent.VK_W){moveUp = false;}
				if(key == KeyEvent.VK_S){moveDown = false;}
			}
			
			@Override
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_D){moveRight = true;}
				if(key == KeyEvent.VK_A){moveLeft = true;}
				if(key == KeyEvent.VK_W){moveUp = true;}
				if(key == KeyEvent.VK_S){moveDown = true;}
			}
		});
		
		setFocusable(true);
	}
	
	private void updatePhysics(){
		//update any player motions
		if(moveRight){x += PLAYER_MOVEMENT_STEP;}
		if(moveLeft){x -= PLAYER_MOVEMENT_STEP;}
		if(moveUp){y -= PLAYER_MOVEMENT_STEP;}
		if(moveDown){y += PLAYER_MOVEMENT_STEP;}
		
		//detect collisions / prevent player from escaping from arena:

		//...with arena walls
		
		
		//...with other players later on...
		
		
	}
	
	private void drawThings(Graphics2D g2d){
		drawPlayer(g2d, x, y);
	}
	
	private void drawPlayer(Graphics2D g2d, double x, double y){
		Ellipse2D e = new Ellipse2D.Double(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(2));
		g2d.fill(e);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		drawThings(g2d);
		
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void addNotify(){
		super.addNotify();
		
		animator = new Thread(this);
		animator.start();
	}

	@Override
	public void run(){
		
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            updatePhysics();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
	}

	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("hettyyytyghy");
	}
}
