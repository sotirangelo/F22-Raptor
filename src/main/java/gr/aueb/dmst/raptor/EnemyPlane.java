package gr.aueb.dmst.corsair;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class EnemyPlane extends Sprite {
	private Vector<Bullet> bullets;

	public EnemyPlane(JPanel panel, String path, int x, int y) {
		super(panel, path, x, y);
		bullets = new Vector<>();
	}
	
    public void fire() {
    	bullets.add(new Missile(getPanel(), "missile1.png", getX() , getY() + getHeight(), 25));
    	bullets.add(new Missile(getPanel(), "missile1.png", getX() + getWidth() , getY() + getHeight(), 25));
    }
    
    public Vector<Bullet> getBullets() {
    	return bullets;
    }
    
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(getImage(),x,y,null);
		if (getY() >= bounds.width) {
			visible = false;
		} else {
			if (getX() >= Window.getPlayerCoords()[0] && getX() <= Window.getPlayerCoords()[0] + Window.getPlayerWidth()) {
				if (bullets.size() < 2) {
					fire();
					for (Bullet b : bullets) {
						((DrawablePanel) getPanel()).addDrawObject(b);
					}
				}
				if (getY() > bounds.height / 3) {
					if (Window.getPlayerCoords()[1] < 0) {
						setDx(3);
						setImage("fighterright.png");
					} else {
						setDx(-3);
						setImage("fighterleft.png");
					}	
				}
			}
			if (getY() >= bounds.height / 2) {
				setDy(10);
			} else {
				setDy(3);
			}
			move();
		}

	}
}
