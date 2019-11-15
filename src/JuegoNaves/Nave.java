package JuegoNaves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

class Nave extends Thread {
	
	// Variables de clase
	private String nomNau;
	private int x, y;
	private int dsx, dsy, v;
	private int tx = 10;
	private int ty = 10;
	private Image image;

	// Constructor
	public Nave (String nomNau, int x, int y, int dsx, int dsy, int v) {
		this.nomNau = nomNau;
		this.x = x;
		this.y = y;
		this.dsx = dsx;
		this.dsy = dsy;
		this.v = v;
		image = new ImageIcon(Nave.class.getResource("nau.png")).getImage();
		Thread t = new Thread(this);
		t.start();
	}

	// Velocidad de las naves
	public int velocitat() {
		return v;
	}

	// Movemos las naves
	public void moure() {

		// Si llega a los márgenes...
		if (x >= 330 - tx || x <= tx - 110) dsx = -dsx;
		if (y >= 400 || y <= ty - 90) dsy = -dsy;
		
		// Movemos la nave automáticamente
		x = x + dsx;
		y = y + dsy;
	}
	
	// Las pintamos
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
			moure();
		}
	}

	// Método para moverse a la izquierda
	public void esquerra() {
		this.dsx = -10;
	}

	// Método para moverse a la dercha
	public void dreta() {
		this.dsx = 10;
	}
	
	public Disparo disparar() {
		// Resto a las posiciones x e y para contrarrestar el tamaño de la imagen
		return new Disparo((this.x - 125), (this.y - 120), 30, 2, 30);
	}
	
	// Cogemos las posiciones de X e Y
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	} 
}

