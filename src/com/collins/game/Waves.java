package com.collins.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Waves {
	
	float x, y;
	int width, height, resolution;
	int incrementBlock;//, incrementY;
	boolean incrementingUp = false;
	float increment = 17;
	float scale;
	
	boolean interpolation = false;

	Color[][] colors;

	public Waves(float x, float y, int width, int height, int resolution, float scale) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.resolution = resolution;
		this.incrementBlock = (int) height/resolution;
		//(int)width/resolution; 
		//this.incrementY = (int) height/resolution;
		this.scale = scale;
		
		colors = new Color[resolution][resolution];
		
		for (int i = 0; i<resolution; i++) {
			for (int j = 0; j<resolution; j++) {
				colors[i][j] = new Color(0,0,0);
			}
		}
		
		
	}
	
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public void draw(Graphics2D g2, Player player) {
		
		increment += 0.7;
		
		for (float y = this.y; y<this.height; y+=incrementBlock) {
			for (float x = this.x; x<this.width; x+=incrementBlock) {
				
				float Val = (int) clamp((float)
						ImprovedNoise.noise(
				(x + player.x +(incrementBlock/2 + (increment/2)))/200 * scale,
				//(x + xPos + (incrementX)/2 + increment)/200 * scale,
				(y + player.y + (incrementBlock)/2 + increment)/200 * scale,
				increment/100) * 400, 0f, 255f);
				
				//System.out.println(clamp ((int)((ImprovedNoise.noise((x+(incrementX/2 + increment))/(25), (y+(incrementY/2 + increment))/(25), 0)) * 300), 0, 255));
				
				Color currentColor = new Color(0, 0, 
						(int) clamp(Val + 80, 0f, 255f));
				
				if (interpolation) {
				
					if (colors[0][0].getBlue() != 0) {
						Color mixedColor;
						
						try {
						mixedColor = new Color((int)((currentColor.getRed() * 0.95) + (colors[(int) ((x)/resolution)][(int) (y/resolution)].getRed() * 0.15)),
							(int)((currentColor.getGreen() * 0.95) + (colors[(int) (x/resolution)][(int) (y/resolution)].getGreen() * 0.15)),
							(int)((currentColor.getBlue() * 0.95) + (colors[(int) (x/resolution)][(int) (y/resolution)].getBlue() * 0.15)));
						} catch(Exception e) {
							//System.out.println((int)((currentColor.getRed() * 0.001) + (colors[(int) (x/resolution)][(int) (y/resolution)].getBlue() * 0.999)));
	
							mixedColor = currentColor;
						}
						//System.out.println(mixedColor.getRed());
											
						g2.setColor(mixedColor);
						
					} else {
						g2.setColor(currentColor);
					}
				
				} else {
					g2.setColor(currentColor);
				}
				
				g2.fillRect((int)x+(incrementBlock/2), (int)y+(incrementBlock/2), incrementBlock, incrementBlock);
				
				if (interpolation) {
				
					try {
						colors[(int) (x/resolution)][(int) (y/resolution)] = currentColor;
					} catch(Exception e) {
						
					}
				
				}
				
			}
		}
		
	}
	
	public Color getPosColor(float xPos, float yPos, float x, float y) {
		
		
		float Val = (int) clamp((float)
				ImprovedNoise.noise(
		(x + xPos+(incrementBlock/2 + (increment/2)))/200 * scale,
		//(x + xPos + (incrementX)/2 + increment)/200 * scale,
		(y + yPos + (incrementBlock)/2 + increment)/200 * scale,
		increment/100) * 400, 0f, 255f);
		
		//System.out.println(clamp ((int)((ImprovedNoise.noise((x+(incrementX/2 + increment))/(25), (y+(incrementY/2 + increment))/(25), 0)) * 300), 0, 255));
		return new Color(0, 0, 
		(int) clamp(Val + 80, 0f, 255f));
		
	}

}
