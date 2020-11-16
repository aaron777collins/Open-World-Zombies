package com.collins.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {

	float x, y, radius, velX, velY;
	int lifeMax, life;
	
	
	public Bullet(float x, float y, float velX, float velY, float radius, int lifeMax) {
		
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.radius = radius;
		
		life = 0;
		this.lifeMax = lifeMax;
		
	}

	public void move() {
		x += velX;
		y += velY;
		
		life++;
	}
	
	public void draw(Graphics2D g2, float playerX, float playerY, float X_OFFSET, float Y_OFFSET) {
		
		g2.setColor(Color.yellow);
		g2.fillOval((int)  (x - playerX - (radius / 2) + X_OFFSET), (int)  (y - playerY - (radius / 2) + Y_OFFSET), (int) (radius), (int)(radius));
		
	}

	public boolean checkLife() {
		return (life > lifeMax);
	}

	public boolean checkCollision(Zombie zombie) {
		
		return (dist(x, y, zombie.x, zombie.y) < zombie.diameter);
		
	}
	
	private double dist(double x1, double y1, double x2, double y2) {
	
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	
	}
}
