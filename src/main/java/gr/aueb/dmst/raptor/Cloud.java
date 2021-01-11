package gr.aueb.dmst.corsair;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Cloud extends Sprite {
	private int x;
	private int y;

	public Cloud(JPanel panel) {
		super(panel);
		selectImage();
		x = (int) (super.bounds.width * Math.random()) + bounds.x;
		setX(x);
		y = -10;
		setY(y);
	}
	
	private void selectImage() {
		int i = (int) (Math.random() * 10);
		if (i < 3) {
			setImage("cloud1.png");
		} else if (i < 6) {
			setImage("cloud2.png");
		} else {
			setImage("cloud3.png");
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(),getX(),getY(),null);
		if (getY() >= bounds.width) {
			visible = false;
		} else {
			setDy(10);
			move();
		}
	}

}
