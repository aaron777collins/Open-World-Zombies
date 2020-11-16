package com.collins.graphics;

import java.awt.Point;
import java.util.ArrayList;

public class SpecialImg {
	
	public ArrayList<SpecialObj> objectList = new ArrayList<SpecialObj>();

	public SpecialImg(ArrayList<SpecialObj> objects) {
		
		for (SpecialObj object : objects) {
			
			objectList.add(object);
			
		}
			
	}
	
	public void rotateAll90() {
		
		for (SpecialObj obj : objectList) {
			
			Point point = new Point();
			point.x = (int) obj.x;
			point.y = (int) obj.y;
			
			TransformationMatrix matrix = new TransformationMatrix();
			
			point = matrix.rotate90(point);
			
			obj.x = (float) point.getX();
			obj.y = (float) point.getY();
			
		}
		
	}
	
	public void rotateAll180() {
		
		rotateAll90();
		rotateAll90();
		
	}
	
public void rotateAll270() {
		
		rotateAll90();
		rotateAll90();
		rotateAll90();
		
	}

}
