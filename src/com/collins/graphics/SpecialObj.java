package com.collins.graphics;

import java.awt.Color;
import java.awt.Dimension;

public class SpecialObj {
	
	public Dimension dimensions;
	public float x, y;
	public Color color;
	public String type;

	public SpecialObj(Dimension dimensions, float x, float y, Color color, String type) {
		
		this.dimensions = dimensions;
		this.x = x;
		this.y = y;
		this.color = color;
		this.type = type;
		
	}

}
