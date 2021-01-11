package gr.aueb.dmst.corsair;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Bullet extends Sprite {
	private int speed;
	
	public Bullet(JPanel panel, String path, int x, int y, int speed) {
		super(panel, path, x, y);
		this.speed = speed;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(),getX(),getY(),null);
		if (getY() < 0) {
			visible = false;
		} else {
			setDy(speed);
			move();
		}
	}

}
