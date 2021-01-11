package gr.aueb.dmst.corsair;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Missile extends Bullet {
	private int speed;
	
	public Missile(JPanel panel, String path, int x, int y, int speed) {
		super(panel, path, x, y, speed);
		this.speed = speed;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(),getX(),getY(),null);
		if (getY() < 0) {
			visible = false;
		} else {
			setDy(speed);
			if (getY() % 2 == 0) {
				setImage("images/missile2.png");
			} else {
				setImage("images/missile1.png");
			}
			move();
		}
	}

}
