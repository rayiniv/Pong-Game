import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class Main extends JFrame{
	
	//Double Buffering
	Image dbImage;
	Graphics dbg;
	
	//Ball object
	static Ball b = new Ball(193, 143);
	
	//Screen size variables
	int GWIDTH = 400;
	int GHEIGHT = 300;
	
	//Screen Dimension
	Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
	
	//Create window (constructor)
	public Main()
	{
		this.setTitle("Pong");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		this.addKeyListener(new AL());
	}
	
	public static void main(String[] args)
	{
		Main m = new Main();
		
		//Create and start thread
		Thread ball = new Thread(b);
		ball.start();
		Thread p1 = new Thread(b.p1);
		Thread p2 = new Thread(b.p2);
		p1.start();
		p2.start();
	}
	
	public void paint(Graphics g)
	{
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g)
	{
		b.draw(g);
		b.p1.draw(g);
		b.p2.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawString(""+b.p1Score, 15, 50);
		g.drawString(""+b.p2Score, 370, 50);
		
		repaint();
	}
	
	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			b.p1.keyPressed(e);
			b.p2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e)
		{
			b.p1.keyReleased(e);
			b.p2.keyReleased(e);
		}
	}
}
