package gr.aueb.dmst.corsair;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class Player extends Sprite {
	private Vector<Bullet> bullets;
		
	public Player(JPanel panel, String path, int x, int y) {
		super(panel, path, x, y);
		bullets = new Vector<>();
	}
	
	@Override
	public void move() {
		x += getDx();
        y += getDy();
	}

	//TODO: Add strafe-fire sprites & Restrict player movement only inside borders
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
        	fire();
        	for (int i = 0; i < bullets.size(); i++) {
        		((DrawablePanel) getPanel()).addDrawObject(bullets.get(i));
        		if (i % 2 == 0) {
        			double rnd = Math.random();
        			if (rnd < 0.3) {
        					setImage("usaplanefire.png");
        			} else if (rnd < 0.6) {
        				setImage("usaplanefire1.png");
        			} else {
        				setImage("usaplanefire.png");
        			}
        		} else {
        			setImage("usaplane.png");
        		}
        	}
        }
        if (key == KeyEvent.VK_LEFT) {
            setDx(-10);
            setImage("usaplaneleft.png");
        }
        if (key == KeyEvent.VK_RIGHT) {
            setDx(10);
            setImage("usaplaneright.png");
        }
        move();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            setImage("usaplane.png");
        }
        if (key == KeyEvent.VK_LEFT) {
            setDx(0);
            setImage("usaplane.png");
        }
        if (key == KeyEvent.VK_RIGHT) {
            setDx(0);
            setImage("usaplane.png");
        }
        move();
    }
    
    public Vector<Bullet> getBullets() {
    	return bullets;
    }
    
    public void removeBullet(Bullet b) {
    	bullets.remove(b);
    	bullets.trimToSize();
    }
    
    
    public void fire() {
    	bullets.add(new Bullet(getPanel(), "bullet.png", x + 21 , y, -50));
    }
}


