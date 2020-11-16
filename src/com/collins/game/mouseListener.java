package com.collins.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseListener implements MouseListener {

	public int x = 0;
	public int y = 0;
	public boolean clicked = false;

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getXOnScreen();
		y = e.getYOnScreen();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicked = true;
		x = e.getXOnScreen();
		y = e.getYOnScreen();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked = false;
		x = e.getXOnScreen();
		y = e.getYOnScreen();
		
	}

}
