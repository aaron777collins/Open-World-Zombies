package com.collins.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.collins.graphics.SpecialImg;
import com.collins.graphics.SpecialObj;

public class Zombie {
	
	float x, y, diameter, speed;
	Color color;
	int direction = 0;
	int attackDistance;

	public Zombie(float x, float y, float diameter, float speed, Color color, int attackDistance) {

		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.speed = speed;
		this.color = color;
		this.attackDistance = attackDistance;
		
	}
	
	public void draw(Graphics2D g2, float playerX, float playerY, int X_OFFSET, int Y_OFFSET) {
		
		//g2.setColor(color);
		
		//g2.fillOval((int) (x - playerX -(diameter/2) + X_OFFSET), (int) (y - playerY -(diameter/2) + Y_OFFSET), (int) diameter, (int) diameter);
		
		ArrayList<SpecialObj> objs = new ArrayList<SpecialObj>();
		
		//bottom hand
				objs.add(
							new SpecialObj(
									new Dimension((int) (diameter/2.5), (int) (diameter/2.5))
									, (int)(10),
									(int) (25),
									new Color(65, 119, 51),
									"Ellipse"
									));
				//top hand
				objs.add(
						new SpecialObj(
								new Dimension((int) (diameter/2.5), (int) (diameter/2.5))
								, (int)(10),
								(int)(-25),
								new Color(65, 119, 51),
								"Ellipse"
								));
				//add body
				objs.add(
						new SpecialObj(
								new Dimension((int) diameter, (int) diameter)
								, 0,
								0,
								Color.green,
								"Ellipse"
								));
				//add helmet
				objs.add(
						new SpecialObj(
								new Dimension((int) diameter, (int) diameter)
								, -1,
								0,
								//new Color(68, 57, 43),
								new Color(65, 119, 51),
								"Ellipse"
								));
				
				SpecialImg img = new SpecialImg(objs);
				
				if (direction < 2) {
					// do nothing
				} else if(direction < 4) {
					
					img.rotateAll90();
					
				} else if (direction < 6) {
					
					img.rotateAll180();
					
				} else if (direction < 8) {
					
					img.rotateAll270();
					
				}
				
				for (SpecialObj object: img.objectList) {
					
					if (object.type == "Ellipse") {
						g2.setColor(object.color);
						g2.fillOval((int)  (object.x + x - playerX -(object.dimensions.width/2) + X_OFFSET), (int) (object.y + y - playerY -(object.dimensions.height/2) + Y_OFFSET), object.dimensions.width, object.dimensions.height);
					}
					
				}
		
	}
	
	public void makeMove(Player player, int X_OFFSET, int Y_OFFSET) {
		
		float dist = (float) Math.sqrt((Math.pow((0 - (x - player.x -(diameter/2))), 2) + Math.pow((0 - (y - player.y -(diameter/2))), 2)));
		
		if (attackDistance > dist) {
			
			float attackSpeed = speed*2;
			
			float zombiePosX = (x - player.x + X_OFFSET);
			float zombiePosY = (y - player.y  + Y_OFFSET);
			
			if(zombiePosX > X_OFFSET+attackSpeed) {
				
				x-= attackSpeed;
				direction = 4;
				
			} else if (zombiePosX < X_OFFSET - attackSpeed) {
				x+= attackSpeed;
				direction = 0;
			}
			
			if(zombiePosY > Y_OFFSET + attackSpeed) {
				
				y-= attackSpeed;
				direction = 6;
				
			} else if(zombiePosY < Y_OFFSET - attackSpeed){
				y+= attackSpeed;
				direction = 2;
			}
			
			
			return;
			
		}
		
		int chosenDirection = new Random().nextInt((int) (120 / speed));
		
		if (chosenDirection == 0) {
			
			direction -= 1;
			
		}
		
		//making zombie choose straight most of the time
		if (chosenDirection == (int) (119 / speed)) {
			direction += 1;
		}
		
		if (direction < 0) {
			direction = 7;
		}
		
		if (direction > 7) {
			
			direction = 0;
			
		}
		
		if (direction == 0) {
			
			move(speed, 0);
			
		}
		if (direction == 1) {
			
			move(speed, speed);
			
		}
		if (direction == 2) {
			
			move(0, speed);
			
		}
		if (direction == 3) {
			
			move(-speed, speed);
			
		}
		if (direction == 4) {
			
			move(-speed, 0);
			
		}
		if (direction == 5) {
			
			move(-speed, -speed);
			
		}
		if (direction == 6) {
			
			move(0, -speed);
			
		}
		if (direction == 7) {
			
			move(speed, -speed);
			
		}
		
		
		
	}
	
	public void move(float dx, float dy) {
		
		x += dx;
		y += dy;
		
		
	}

}
