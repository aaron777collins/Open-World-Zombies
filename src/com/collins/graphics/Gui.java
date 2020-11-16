package com.collins.graphics;

import java.awt.Graphics2D;

public class Gui {

	int x, y;
	
	public Gui(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}

	public void draw(int panelX, int panelY, Graphics2D g2) {
		
		g2.drawRect(x + panelX, y + panelY, 10, 10);
		
	}


}
