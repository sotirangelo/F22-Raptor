package gr.aueb.dmst.corsair;

import java.awt.TextComponent;
import java.awt.TextField;

import javax.swing.JMenuBar;

public class HUD extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private TextComponent kills;
	private TextComponent msg;
	
	public HUD() {
		kills = new TextField("Kills: 0");
		add(kills);
		kills.setEditable(false);
		msg = new TextField("WORK IN PROGRESS");
		add(msg);
		msg.setEditable(false);
	}
	
	public void setText(String score) {
		kills.setText(score);
	}
	
}
