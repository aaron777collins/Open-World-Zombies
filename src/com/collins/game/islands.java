package com.collins.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class islands {
	
	float x, y;
	int width, height, resolution;
	int incrementBlock;//, incrementY;
	boolean incrementingUp = false;
	float scale;

	public islands(float x, float y, int width, int height, int resolution, float scale) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.resolution = resolution;
		this.incrementBlock = (int) height / resolution;
		//= (int)width/resolution;
		//this.incrementY = (int) height/resolution;
		this.scale = scale;
		
		
	}
	
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public boolean onSolid(float xPos, float yPos, float x, float y) {
		
		float Val = (int) clamp((float)
				ImprovedNoise.noise(
		(x + xPos+(incrementBlock/2))/200 / scale,
		(y + yPos + (incrementBlock)/2)/200 / scale, 0) * 400, 0f, 255f);
		
		if (Val > 50) {
			
			return true;
			
		}
		return false;
		
	}
	
	public float getHeight(float xPos, float yPos, float x, float y) {
		
		float Val = (int) clamp((float)
				ImprovedNoise.noise(
		(x + xPos+(incrementBlock/2))/200 / scale,
		(y + yPos + (incrementBlock)/2)/200 / scale, 0) * 400, 0f, 255f);
		
		return Val;
		
	}
	
	public void draw(Graphics2D g2, Player player, Waves waves) {
		
		for (float y = this.y; y<this.height; y+=incrementBlock) {
			for (float x = this.x; x<this.width; x+=incrementBlock) {
				
				float Val = (int) clamp((float)
						ImprovedNoise.noise(
				(x + player.x+(incrementBlock/2))/200 / scale,
				(y + player.y + (incrementBlock)/2)/200 / scale, 0) * 400, 0f, 255f);
				
				//System.out.println(clamp ((int)((ImprovedNoise.noise((x+(incrementBlock/2 + increment))/(25), (y+(incrementBlock/2 + increment))/(25), 0)) * 300), 0, 255));
				
				//sand color
				g2.setColor(new Color((int) clamp(Val, 0f, 255f), (int) clamp(Val, 0f, 255f), 0));
				if (Val < 50) {
					
					continue;
					
				}
				
				if (Val > 130) {
					
					//draw dark green 
					g2.setColor(new Color((int) 50, (int) clamp(Val, 0, 170), (int) 50));
					
					if (Val < 165) {
						
						//draw light green
						g2.setColor(new Color((int) (50 + clamp(Val, 0f, 255f))/2, (int) clamp(Val, 0, 255), (int) 50/2));
					}
					
				}
				
				if (Val < 100) {
					
					//blend water
					
					float waveRed = (float) (waves.getPosColor(player.x, player.y, x, y).getRed());
					float waveGreen = (float) (waves.getPosColor(player.x, player.y, x, y).getGreen());
					float waveBlue = (float) (waves.getPosColor(player.x, player.y, x, y).getBlue());
																									//(val/47) is the blending from water and sand mix to just sand
					int red = (int) (waveRed + (clamp(Val * (Val/50), 0f, 255f))/2);
					int green = (int) (waveGreen  + clamp(Val * (Val/50), 0f, 255f)/2);
					int blue = (int) waveBlue / 2;
										
					g2.setColor(new Color ((int)clamp(red, 0, 255), (int)clamp(green, 0, 255), (int)clamp(blue, 0, 255)));
					
					
				}
				
				g2.fillRect((int)x+(incrementBlock/2), (int)y+(incrementBlock/2), incrementBlock, incrementBlock);
			}
		}
		
	}

}
