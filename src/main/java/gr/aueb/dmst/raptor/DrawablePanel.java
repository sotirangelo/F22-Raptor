package gr.aueb.dmst.corsair;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Vector;

public class DrawablePanel extends JPanel implements Runnable {
	private Vector<Drawable> drawObjects;
	private Thread thread;
	private static final long serialVersionUID = 1L;
	
	public DrawablePanel() {
		initializeThread();
		drawObjects = new Vector<Drawable>();
	}
	
	private void initializeThread() {
			thread = new Thread(this);
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
	}

	public void addDrawObject(Drawable drawObject) {
		drawObjects.add(drawObject);
	}
	
	public Vector<Drawable> getDrawables() {
		return new Vector<Drawable>(drawObjects);
	}
	
	public void removeDrawObject(Drawable d) {
		drawObjects.remove(d);
		drawObjects.trimToSize();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < drawObjects.size(); i++) {
			if (drawObjects.get(i).isVisible()) {
				drawObjects.get(i).draw(g);
			} else {
				if (drawObjects.get(i) instanceof Player) {
					Window.drawGameOver(g); //XXX: Improve Game Over Screen
				} else {
				drawObjects.remove(i);
				}
			}
		}
	}

	@Override
	public synchronized void run() {
		Thread me = Thread.currentThread();
		while (me == thread) {
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		thread = null;
	}
	
	public void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


