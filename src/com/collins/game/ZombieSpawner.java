package com.collins.game;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import com.collins.math.maths;

public class ZombieSpawner {

	int ticker = 0;
	
	public Dimension getNewZombiePos(Player player) {

		Random random = new Random();
		int valX = random.nextInt(1601) - 800;
		int valY = random.nextInt(1601) - 800;
		
		float newX = player.x + valX;
		float newY = player.y + valY;
		
		if (maths.getDistance(player.x, player.y, newX, newY) < 300) {
			return null;
		}
		
		return new Dimension((int) newX, (int) newY);
		

	}
	
	public void update(Player player, ArrayList<Zombie> zombies) {
		
		ticker++;
		
		if (ticker > 250) {
				
			if (zombies.size() > 25) {
				
				for (Zombie zombie: zombies) {
					
					if (maths.getDistance(zombie.x, zombie.y, player.x, player.y) > 1000) {
						
						zombies.remove(zombie);
						return;
						
					}
					
				}
				
			} else {
				
				Dimension pos = getNewZombiePos(player);
				
				while(pos == null) {
					
					pos = getNewZombiePos(player);
					
				}
				
				zombies.add(new Zombie((float) pos.getWidth(), (float) pos.getHeight(), 40, 1.5f, Color.black, 400));
				
			}
			ticker = 0;
		} 
		
		
	}

}
