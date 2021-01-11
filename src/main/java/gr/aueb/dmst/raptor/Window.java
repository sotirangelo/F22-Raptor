package gr.aueb.dmst.corsair;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	protected static final int WIDTH = 1024;
	protected static final int HEIGHT = 768;
	protected static final Color seaColor = new Color(11, 68, 154);
	private static DrawablePanel sea;
	private static DrawablePanel sky;
	public static Player player;
	private static Vector<EnemyPlane> enemies;
	private static Thread thread;
	private static HUD score;
	private static int kills;
	public static boolean gameover = false;
	
	public Window() {
		super("F22-Raptor");
		initializeGraphics();
		this.addKeyListener(new TAdapter());
		player = new Player(getSkyCanvas(), "usaplane.png", (getWidth() / 2), (getHeight() / 2 + ((getHeight() / 2) / 2)));
		sky.addDrawObject(player);
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();

	}
	
	private void initializeGraphics() {
		Game.playSound("sounds/intro.wav");
		JFrame.setDefaultLookAndFeelDecorated(true);
		sky = new DrawablePanel();
		sky.setOpaque(false);
		setGlassPane(sky);
		sky.setVisible(true);
		sea = new DrawablePanel();
		sea.setBackground(seaColor);
		sea.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(sea);
		score = new HUD();
		setJMenuBar(score);
		enemies = new Vector<EnemyPlane>();
		setDefaultCloseOperation(
			javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
		setSize(WIDTH, HEIGHT);
		pack();
		setVisible(true);
		setFocusable(true);
	}

	public JPanel getSeaCanvas() {
		return sea;
	}
	
	public JPanel getSkyCanvas() {
		return sky;
	}
	
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
           player.keyPressed(e);  
        }

        @Override
        public void keyReleased(KeyEvent e) {
        	player.keyReleased(e);
        }
    }
	
	public static int[] getPlayerCoords() {
		int[] array = {player.getX(), player.getDx(), player.getY()};
		return array;
	}

	public static int getPlayerWidth() {
		return player.getWidth();
	}
	
	public static void addEnemy(EnemyPlane e) {
		enemies.add(e);
		e.setVisible(true);		
		sky.addDrawObject(e);
	}
	
	public static void removeEnemy(EnemyPlane e) {
		enemies.remove(e);
		enemies.trimToSize();
	}
	
	public static Vector<EnemyPlane> getEnemies() {
		return enemies;
	}
	
	public void checkCollisions() {
		Rectangle r1 = player.getBounds();
		for (int i = 0 ; i < player.getBullets().size() ; i++) {
			Rectangle r2 = player.getBullets().get(i).getBounds();
			for (int j = 0 ; j < enemies.size() ; j++) {
				Rectangle r3 = enemies.get(j).getBounds();
				if (r2.intersects(r3)) {
					player.getBullets().get(i).setVisible(false);
					player.getBullets().remove(i);
					enemies.get(j).setVisible(false);
					var e = new Explosion(enemies.get(j).getPanel(),"explosion1.png", enemies.get(j).getX(), enemies.get(j).getY());
					((DrawablePanel) e.getPanel()).addDrawObject(e);
					enemies.remove(j);
					increaseKills();
				}
			}
		}
		for (EnemyPlane e : enemies) {
			Rectangle r3 = e.getBounds();
			if (r3.intersects(r1)) {
				player.setVisible(false);
				var ex = new Explosion(player.getPanel(),"explosion1.png", player.getX(), player.getY());
				((DrawablePanel) ex.getPanel()).addDrawObject(ex);
				e.setVisible(false);
				removeEnemy(e);
				break;
			}
		}
		for (int i = 0 ; i < enemies.size() ; i++) {
			Rectangle r3 = enemies.get(i).getBounds();
			if (r3.intersects(r1)) {
				player.setVisible(false);
				enemies.get(i).setVisible(false);
				enemies.remove(i);
			}
			for (int j = 0 ; j < enemies.get(i).getBullets().size() ; j++) {
				Rectangle r2 = enemies.get(i).getBullets().get(j).getBounds();
				if (r1.intersects(r2)) {
					enemies.get(i).getBullets().get(j).setVisible(false);
					enemies.get(i).getBullets().remove(j);
					player.setVisible(false);
					var e = new Explosion(player.getPanel(),"explosion1.png", player.getX(), player.getY());
					((DrawablePanel) e.getPanel()).addDrawObject(e);
				}
				
			}
		}
	}
	
	public void increaseKills() {
		kills++;
		score.setText("Kills: " + kills);
		score.repaint();
	}
	
	public static void drawGameOver(Graphics g) {
		gameover = true;
		g.setColor(Color.white);
		g.drawString("Game Over", WIDTH / 2, HEIGHT / 2);
	}

	@Override
	public void run() {
		Thread me = Thread.currentThread();
		while (me == thread) {
			checkCollisions();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		
	}
}
