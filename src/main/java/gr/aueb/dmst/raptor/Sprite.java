package gr.aueb.dmst.corsair;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class Sprite extends Drawable {
    private Image image;
    private int dx;
    private int dy;
    
    public Sprite(JPanel panel, String path, int x, int y) {
        super(panel);
        setX(x);
        setY(y);
        loadImage(path);
    }
    
    public Sprite(JPanel panel, String path) {
        super(panel);
        loadImage(path);
    }
    public Sprite(JPanel panel) {
        super(panel);
    }
    
    public void move() {
        x += dx;
        y += dy;
   //     getPanel().repaint(x - 1, y - 1, w + 2, h + 2);
    }

    private void loadImage(String path) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        super.w = image.getWidth(null);
        super.h = image.getHeight(null);
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x) {
    	this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
    public int getWidth() {
        return w;
    }
    
    public int getHeight() {
        return h;
    }    

    public Image getImage() {
        return image;
    }   
    
    public void setImage(String path) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        super.w = image.getWidth(null);
        super.h = image.getHeight(null);
    }

    public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void draw(Graphics g) {
	//	char displayChar = ((y % 5) == 0) ? '-' : '~';
	//	g.setColor(new Color(255, 255, 255));
	//	g.drawString((Character.valueOf(displayChar)).toString(), x, y);
		g.drawImage(image,x,y,null);
	}	
}
