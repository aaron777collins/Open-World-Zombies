package com.collins.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.collins.graphics.SpecialImg;
import com.collins.graphics.SpecialObj;



public class Player {

	float x, y, velX, velY, screenPosX, screenPosY, diameter, speed;
	int direction = 0;
	
	Gun gun = new Gun(13, 21, 25, 7.5);
	
	public Player(float x, float y, float screenPosX, float screenPosY, float diameter, float speed) {
	
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.screenPosX = screenPosX;
		this.screenPosY = screenPosY;
		this.speed = speed;
		
	
	}
	
	public void move(float dx, float dy, islands island) {
		
		if (!island.onSolid(x, y, Main.WIDTH/2, Main.HEIGHT/2) ) {
			dx = (int)(dx/2);
			dy = (int)(dy/2);
		}
		
		velX = dx;
		velY = dy;
		
		x += dx;
		y += dy;
		
	}
	
	public void draw(Graphics2D g2, int X_OFFSET, int Y_OFFSET) {
		
		//g2.setColor(new Color(114, 88, 57));
		
		//g2.fillOval((int) (screenPosX-(diameter/2)), (int) (screenPosY-(diameter/2)), (int) diameter, (int) diameter);
		
		int xOff = (int) (screenPosX);
		int yOff = (int) (screenPosY);
		
		ArrayList<SpecialObj> objs = new ArrayList<SpecialObj>();
		//bottom hand
		objs.add(
					new SpecialObj(
							new Dimension((int) (diameter/2.5), (int) (diameter/2.5))
							, (int)(10),
							(int) (25),
							Color.black,
							"Ellipse"
							));
		//top hand
		objs.add(
				new SpecialObj(
						new Dimension((int) (diameter/2.5), (int) (diameter/2.5))
						, (int)(10),
						(int)(-25),
						Color.black,
						"Ellipse"
						));
		//add body
		objs.add(
				new SpecialObj(
						new Dimension((int) diameter, (int) diameter)
						, 0,
						0,
						new Color(224, 173, 109),
						"Ellipse"
						));
		//add helmet
		objs.add(
				new SpecialObj(
						new Dimension((int) diameter, (int) diameter)
						, -1,
						0,
						//new Color(68, 57, 43),
						 new Color(76, 60, 35),
						"Ellipse"
						));
		
		SpecialImg img = new SpecialImg(objs);
		
		if (direction == 0) {
			// do nothing
		} else if(direction == 1) {
			
			img.rotateAll90();
			
		} else if (direction == 2) {
			
			img.rotateAll180();
			
		} else if (direction == 3) {
			
			img.rotateAll270();
			
		}
		
		for (SpecialObj object: img.objectList) {
			
			if (object.type == "Ellipse") {
				g2.setColor(object.color);
				g2.fillOval((int) object.x - object.dimensions.width / 2 + xOff, (int) object.y + yOff - object.dimensions.height / 2, object.dimensions.width, object.dimensions.height);
			}
			
		}
		
		gun.draw(g2, direction, x, y, X_OFFSET, Y_OFFSET);
		
		
	}

}
