package com.collins.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HealthBar extends Gui {
	
	int width, height, fontSize;
	String header;
	double health;

	public HealthBar(int x, int y, int width, int height, String header, int fontSize, double health) {
		super(x, y);
		
		this.width = width;
		this.height = height;
		this.header = header;
		this.fontSize = fontSize;
		this.health = health;
		
	}
	
	@Override
	public void draw(int panelX, int panelY, Graphics2D g2) {

		Font font = new Font("Arial", Font.PLAIN, fontSize);
		
		g2.setColor(Color.green);
		g2.fillRect(x + panelX, y + panelY, width, height);
		
		g2.setFont(font);
		g2.setColor(Color.white);
		g2.drawString(header, x + panelX, y + panelY - (fontSize/2));
		
	}

}
