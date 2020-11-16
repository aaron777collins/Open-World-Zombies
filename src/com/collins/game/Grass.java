package com.collins.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grass {

	public void draw(Graphics2D g2, islands island, Player player) {
		
		for (int i = 0; i<Main.WIDTH; i+=3) {
			
			for (int j = 0; j<Main.HEIGHT; j+=3) {
				
				Color grass = new Color(34, 132, 47);
				
				int iTemp = (int) (i - (player.x % 15));
				int jTemp = (int) (j - (player.y % 15));
				//int jTemp = (int) ((j - playerY) % 15) + j;
				
				if (island.getHeight(player.x, player.y , i, j) > 160) {
					if (i % (15) == 0 && j % (15) == 0) {
												
						g2.setColor(grass);
						g2.fillRect(iTemp, jTemp, 3, 8);
						g2.fillRect(iTemp + 1, jTemp - 2, 2, 2);
						
					}
					
					/*
					if ((i + 5) % (15) == 0 && (j + 5) % (15) == 0) {
						
						g2.setColor(grass);
						g2.fillRect(iTemp + 7, jTemp + 7, 3, 8);
						g2.fillRect(iTemp + 7 + 1, jTemp + 7 - 2, 2, 2);
						
					}
					*/
					
					
				}
				
			}
				
		}
			
	}
		
}

