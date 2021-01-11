package gr.aueb.dmst.corsair;

import javax.swing.JPanel;

public class Explosion extends Sprite implements Runnable {
	private Thread thread;
	
	public Explosion(JPanel panel, String path, int x, int y) {
		super(panel, path, x, y);
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	@Override
	public void run() {
		try {	
			setImage("explosion2.png");
			Thread.sleep(200);
			setImage("explosion1.png");
			Thread.sleep(200);
			setImage("explosion2.png");
			Thread.sleep(200);
			setImage("explosion3.png");
			Thread.sleep(200);
			setVisible(false);
			} catch (InterruptedException e) {}
	}
}
