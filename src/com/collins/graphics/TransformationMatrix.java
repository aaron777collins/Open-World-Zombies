package com.collins.graphics;

import java.awt.Point;

public class TransformationMatrix {

	public TransformationMatrix() {

	}
	
	public Point rotate90(Point point) {
		
		Point point2 = new Point();
		
		point2.x = (int) point.getY() * -1;
		point2.y = (int) (point.getX());
		
		return point2;
		
	}
	
	public Point rotate180(Point point) {
		
		point = rotate90(point);
		point = rotate90(point);
		
		return point;
		
	}
	
	public Point rotate270(Point point) {
		
		point = rotate90(point);
		point = rotate90(point);
		point = rotate90(point);
		
		return point;
		
	}

}
