package gr.aueb.dmst.corsair;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game  {
	private static final int NUM_WAVES = 50;
	private static final int[] enemypos = {125, 300, 475, 650, 825};
	
	//XXX: Doesn't look good
	public static void playSound(String soundfile) {
	   try {
		File f = new File("./" + soundfile);
	    AudioInputStream audioIn;
		audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioIn);
	    clip.start();	
	    	} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}  
	}
	
	public static void main(String[] args) {
		Window w = new Window();
		while (true) {
			if (((DrawablePanel) w.getSeaCanvas()).getDrawables().size() < NUM_WAVES) {
				Wave wave = new Wave(w.getSeaCanvas());
				((DrawablePanel) w.getSeaCanvas()).addDrawObject(wave);
			}
			Vector<Drawable> drawables = ((DrawablePanel) w.getSkyCanvas()).getDrawables();
			int count1 = 0;
			for (Drawable d : drawables) {
				if (d instanceof Cloud) {
					count1++;
					
				}
			}
			if (count1 < Math.random() * 5) {
				Cloud cloud = new Cloud(w.getSkyCanvas());
				((DrawablePanel) w.getSkyCanvas()).addDrawObject(cloud);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}
			int count2 = 0;
			for (Drawable d : drawables) {
				if (d instanceof EnemyPlane) {
					count2++;
					
				}
			}
			if (count2 == 0) {
				for (int i = 0 ; i < 5 ; i++) {
					Window.addEnemy(new EnemyPlane(w.getSkyCanvas(), "fighter.png", enemypos[i], 0 ));
				}
				
			}
		}
	}
}

		

