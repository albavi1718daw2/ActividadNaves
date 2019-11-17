package JuegoNaves;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

class PanelNave extends JPanel implements Runnable, KeyListener {

	// Variabes de clase
	private int numNaus = 3;    
	Vector<Nave> nau;
	Nave nauPropia;
	Vector<Disparo> disparo = new Vector<Disparo>();

	public PanelNave() {        

		nau = new Vector<>();

		// Creamos las naves enemigas
		for (int i = 0; i < numNaus; i++) {

			Random rand = new Random();
			int velocitat = (rand.nextInt(3) + 5) * 10;
			int posX = rand.nextInt(100) + 30;
			int posY = rand.nextInt(100) + 30;
			int dX = rand.nextInt(3) + 1;
			int dY = rand.nextInt(3) + 1; 
			String nomNau = Integer.toString(i);
			nau.add(new Nave(nomNau, posX, posY, dX, dY, velocitat));
		}

		// Creación de nuestra nave
		nauPropia = new Nave("NavePeyAle", 200, 400, 10, 0, 100);

		// Hilo para que cada se dibuje el juego por la pantalla
		Thread n = new Thread(this);
		n.start();         

		// Creo los listener para que el hilo principal gestione los clicks
		// del teclado
		addKeyListener(this);
		setFocusable(true);               
	}

	public void run() {

		while(true) {
			try { 
				Thread.sleep(1);
			} catch(Exception e) {}
			repaint();            
		}                   
	}

	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < nau.size(); ++i) nau.get(i).pinta(g);
		nauPropia.pinta(g);

		// Pintamos los disparos
		for (int i = 0; i < disparo.size(); i++) {
			disparo.get(i).pinta(g);
			// Cogemos el disparo y si llega a los márgenes, lo quitamos
			if (disparo.get(i).getY() <= 0) {
				disparo.remove(i);
			}
		}      
		
		// Miramos si el disparo ha tocado a alguna nave
		for (int i = 0; i < disparo.size(); i++) {
			// Cogemos el disparo y sus posiciones
			int posXdisparo = disparo.get(i).getX(); 
			int posYdisparo = disparo.get(i).getY();
			for (int j = 0; j < nau.size(); j++) {
				// Cogemos la nave y sus posiciones
				double calculo;				
				int posXnave = nau.get(j).getX();
				int posYnave = nau.get(j).getY();
				calculo = Math.sqrt(Math.pow((posXnave - posXdisparo), 2) +
					Math.pow((posYnave - posYdisparo), 2));
				if (calculo <= 20) {
					nau.remove(j);					
					disparo.remove(i);
				}
				// Si la cantidad de naves es 0, hemos ganado
				if (nau.size() == 0) {
					System.out.println("¡Has ganado!");
					System.exit(0);  
				}
			}
		}
	}
            
	// Métodos necesarios para detectar los movimientos del teclado
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Flecha izquierda
		if (e.getKeyCode() == 37) { 
			nauPropia.esquerra();
		}
		// Flecha derecha
		if (e.getKeyCode() == 39) { 
			nauPropia.dreta(); 
		}      
		// Espacio
		if (e.getKeyCode() == 32) { 
			disparo.add(nauPropia.disparar());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}


