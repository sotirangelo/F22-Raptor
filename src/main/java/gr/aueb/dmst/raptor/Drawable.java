package gr.aueb.dmst.corsair;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Drawable {
	protected Graphics2D canvas;
	protected Rectangle bounds;
	protected boolean visible;
	private JPanel panel;
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Drawable(JPanel panel) {
		this.panel = panel;
		bounds = panel.getBounds();
		canvas = (Graphics2D)panel.getGraphics();
		visible = true;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public abstract void draw(Graphics g);
}
