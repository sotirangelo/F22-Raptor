package gr.aueb.dmst.corsair;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Wave extends Drawable {
	private static final Color white = new Color(255, 255, 255);
	private int x;
	private int originalY;
	private int y;
	private char displayChar;
	private int duration = (int) (3 + Math.random() * (Math.random() * 50));
	
	public Wave(JPanel panel) {
		super(panel);
		x = (int) (super.bounds.width * Math.random()) + bounds.x;
		y = (int) (super.bounds.height * Math.random()) + bounds.y;
		originalY = y;
	}
	
	@Override
	public void draw(Graphics g) {
		
		displayChar = ((x % 2) == 0) ? '-' : '~';
		g.setColor(white);
		g.drawString((Character.valueOf(displayChar)).toString(), x, y);
		if (y >= bounds.width) {
			visible = false;
		}
		if ( y <  originalY + duration) {
			y += (int) (Math.random() * 10);
		}
		else {
			visible = false;
		}
	}

	public int getDuration() {
		return duration;
	}

}
