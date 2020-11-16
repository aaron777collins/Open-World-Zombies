package com.collins.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends Gui{

	int x, y, width, height;
	Icon unclicked, hovering, clicked;
	JButton button;
	
	boolean drawedOnce = false;
	
	public Menu (JFrame frame, int x, int y, int width, int height, Icon unclicked, Icon hovering, Icon clicked) {
		
		super(x, y);
		
		this.width = width;
		this.height = height;
		
		this.unclicked = unclicked;
		this.clicked = clicked;
		this.hovering = hovering;
		
		button = new JButton();
		button.setLocation(x, y);
		button.setSize(width, height);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0,0,0,0));
		button.setIcon(unclicked);
		button.setRolloverIcon(hovering);
		button.setPressedIcon(clicked);
		button.setDisabledIcon(unclicked);

		frame.add(button);
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		button.paint(g2);
	}

}
