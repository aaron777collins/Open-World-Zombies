package com.collins.game;

import java.util.ArrayList;

public class BulletManager {

	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public void moveBullets(ArrayList<Zombie> zombies) {
		
		for (Bullet b : bullets) {
			
			b.move();
			
			
			if (b.checkLife()) {
				bullets.remove(b);
				return;
			}
			
			for(Zombie zombie : zombies) {
				if (b.checkCollision(zombie)) {
					bullets.remove(b);
					zombies.remove(zombie);
					return;
				}
			}
			
		}
		
	}

}
