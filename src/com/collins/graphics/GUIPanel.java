package com.collins.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GUIPanel {

	int x, y, width, height;
	ArrayList<Gui> guis;
	
	public GUIPanel(int x, int y, int width, int height, ArrayList<Gui> guis) {
		
		this.guis = guis;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void draw(Graphics2D g2) {
		
		for (Gui gui : guis) {
			
			Color color = new Color(107, 107, 107, 240);
			
			g2.setColor(color);
			g2.fillRect(x, y, width, height);
			gui.draw(x, y, g2);
			
		}
		
	}

}
