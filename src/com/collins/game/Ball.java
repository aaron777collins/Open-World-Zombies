package com.collins.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	
	float x, y, radius;
	float xVel, yVel = 0;
	Color color;
	int lifeTime = 0;

	public Ball(float x, float y, float radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		
	}
	
	public void draw(Graphics2D g2, float xPos, float yPos) {
		
		g2.setColor(color);
		g2.fillOval((int) (x - xPos), (int) (y - yPos), (int) radius, (int) radius);
		
		
	}
	
	public void update(islands island, float xPos, float yPos) {

		if ((xVel <= 1 && yVel <= 1) && (xVel >= -1 && yVel >= -1)) {
			lifeTime++;
		}
		
		float checkDist = 3f;
		
		//getting height beside ball
		float posX1 = island.getHeight(xPos, yPos, (x - xPos) + checkDist, (y - yPos));
		float posX2 = island.getHeight(xPos, yPos, (x - xPos) - checkDist, (y - yPos));
		
		//getting height above ball (up and down)
		//float posY1 = waves.getPosColor(x, y + 1, Main.WIDTH, Main.HEIGHT).getBlue();
		float posY1 = island.getHeight(xPos, yPos, (x - xPos), (y - yPos) + checkDist);
		float posY2 = island.getHeight(xPos, yPos, (x - xPos), (y - yPos) - checkDist);
		
		xVel += (posX2 - posX1);
		yVel += (posY2 - posY1);
		
		x += xVel;
		y += yVel;
		
		xVel *= 0.85;
		yVel *= 0.85;
		
	}

}
