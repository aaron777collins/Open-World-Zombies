package com.collins.math;

public interface maths {
	
	public static float getDistance(float x, float y, float x2, float y2) {
		
		float value = (float) Math.sqrt(Math.pow((x2 - x), 2) + Math.pow(y2-y, 2));
		
		return value;
		
	}

}
