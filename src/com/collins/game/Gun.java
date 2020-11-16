package com.collins.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.collins.graphics.SpecialImg;
import com.collins.graphics.SpecialObj;

public class Gun {

	private float x, y;
	public float finalX, finalY;
	private double width, height;
	
	public Gun(float x, float y, double width, double height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void move(float dx, float dy) {
		
		this.x += dx;
		this.y += dy;
		
	}
	
	public void setPosition(float x, float y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void draw(Graphics2D g2, int dir, float playerX, float playerY, int X_OFFSET, int Y_OFFSET) {
		
		ArrayList<SpecialObj> objs = new ArrayList<SpecialObj>();

		
		objs.add(
					new SpecialObj(
							new Dimension((int) (width), (int) (height))
							, (int)(x),
							(int) (y),
							Color.gray,
							"Rectangle"
							));
		
		SpecialImg img = new SpecialImg(objs);
		
		if (dir == 0) {
			// do nothing
		} else if(dir == 1) {
			
			img.rotateAll90();
			
		} else if (dir == 2) {
			
			img.rotateAll180();
			
		} else if (dir == 3) {
			
			img.rotateAll270();
			
		}
		
		for (SpecialObj object: img.objectList) {
			
			if (object.type == "Rectangle") {
				g2.setColor(object.color);
				//g2.fillOval((int) object.x - object.dimensions.width / 2 + xOff, (int) object.y + yOff - object.dimensions.height / 2, object.dimensions.width, object.dimensions.height);
				
				if(dir == 0) {
					finalX = (int) (object.x + X_OFFSET);
					finalY = (int) (object.y + Y_OFFSET);
					g2.fillRect((int) (object.x + X_OFFSET), (int) (object.y + Y_OFFSET), object.dimensions.width, object.dimensions.height);
				} else if(dir == 1) {
					finalX = (int) (object.x + X_OFFSET - object.dimensions.height);
					finalY = (int) (object.y + Y_OFFSET);
					g2.fillRect((int) (object.x + X_OFFSET - object.dimensions.height), (int) (object.y + Y_OFFSET), object.dimensions.height, object.dimensions.width);
				} else if(dir == 2) {
					finalX = (int) (object.x + X_OFFSET - object.dimensions.width);
					finalY = (int) (object.y + Y_OFFSET - object.dimensions.height);
					g2.fillRect((int) (object.x + X_OFFSET - object.dimensions.width), (int) (object.y + Y_OFFSET - object.dimensions.height), object.dimensions.width, object.dimensions.height);
				} else if(dir == 3) {
					finalX =(int) (object.x + X_OFFSET);
					finalY = (int) (object.y + Y_OFFSET - object.dimensions.width);
					g2.fillRect((int) (object.x + X_OFFSET), (int) (object.y + Y_OFFSET - object.dimensions.width), object.dimensions.height, object.dimensions.width);
				}
				
			}
			
		}
	}

}
