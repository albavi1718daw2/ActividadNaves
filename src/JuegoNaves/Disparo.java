package JuegoNaves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Disparo extends Thread {

	// Variables de clase
	private int x, y;
	private int dsx, dsy, v;
	private int tx = 10;
	private int ty = 10;
	private Image image;

	// Constructor
	public Disparo (int x, int y, int dsx, int dsy, int v) {
		this.x = x;
		this.y = y;
		this.dsx = dsx;
		this.dsy = dsy;
		this.v = v;
		image = new ImageIcon(Nave.class.getResource("bala.png")).getImage();
		Thread t = new Thread(this);
		t.start();
	}

	// Pintamos el láser
	public void pinta (Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.image, x, y, null);
	}
	
	public void run() {

		while (true) {
			try { 
				Thread.sleep(this.v); 
			} 
			catch (Exception e) {

			}
			disparo();
		}
	}

	// Movemos el disparo
	public void disparo() {
		y = y - ty;
	}

	// Cogemos las posiciones de X e Y
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	// Cogemos la velocidad
	 public int velocitat () { 
	    return v;
	  }
}
