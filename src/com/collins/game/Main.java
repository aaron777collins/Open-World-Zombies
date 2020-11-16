package com.collins.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.collins.graphics.GUIPanel;
import com.collins.graphics.Gui;
import com.collins.graphics.HealthBar;
import com.collins.graphics.Menu;
import com.collins.io.imageLoader;

public class Main extends JPanel implements Runnable {
	
	public boolean running = true;
	private static final long serialVersionUID = -4781731301054522632L;
	JFrame frame;
	public static Main main;
	static int WIDTH = 1200;
	static int HEIGHT = 640;
	Grass grass;
	Waves waves;
	islands islands;
	ZombieSpawner spawner = new ZombieSpawner();
	
	public static float bulletSpeed = 20;
		
	public int X_OFFSET = WIDTH/2;
	public int Y_OFFSET = HEIGHT/2;
	
	public BulletManager bulletM;
	
	Menu menu;
	
	Player player;
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	String TITLE = "Game";
	
	//200 max balls
	ArrayList<Ball> balls = new ArrayList<Ball>();
	
	JOptionPane resQuestion = new JOptionPane();
	
	GUIPanel panel;
	
	/*
	//asks for resolution
	int RESOLUTION = Integer.parseInt( (String) JOptionPane.showInputDialog(resQuestion,
	        "Resolution? (Recommended Between 100-200)",
	        "Setting Up \"" + TITLE + "\"", JOptionPane.QUESTION_MESSAGE,
	        null,
	        null,
	        "100"));
	*/
	//hardcoded for reasons of balls moving very fast
	int RESOLUTION = 100;
	keyHandler keys;
	mouseListener mouse;
	
	Dimension dimensions = new Dimension(WIDTH, HEIGHT);
	
	public Main() {
		
		keys = new keyHandler(main);
		mouse = new mouseListener();
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.addKeyListener(keys);
		frame.addMouseListener(mouse);
		frame.setPreferredSize(dimensions);
		frame.pack();
		frame.setVisible(true);
		
		waves = new Waves(0, 0, WIDTH, HEIGHT, RESOLUTION, 10);
		islands = new islands(0, 0, WIDTH, HEIGHT, RESOLUTION, 10);
		grass = new Grass();
		
		player = new Player(2000, -2000, WIDTH/2, HEIGHT/2, 40, 10);
		//player = new Player(0, 0, WIDTH/2, HEIGHT/2, 40, 5);
		
		bulletM = new BulletManager();
		
		//making menu
		
		//importing images
		
		imageLoader loader = new imageLoader();
		
		Icon icon1 = new ImageIcon(loader.loadBufferedImage("/icon/menu1.png"));
		Icon icon2 = new ImageIcon(loader.loadBufferedImage("/icon/menu2.png"));
		
		menu = new Menu(frame, 300, 0, 256, 128, icon1, icon1, icon2);
		
		ArrayList<Gui> guis = new ArrayList<Gui>();
		guis.add(new HealthBar(10, 25, 220, 25, "Health Bar", 14, 100));
		
		panel = new GUIPanel(6, 3, 250, 65, guis);
		
		Thread thread = new Thread(this);
		thread.start();
		
	}
	
	public static void main(String[] args) {
		main = new Main();

	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.clearRect(0, 0, WIDTH, HEIGHT);
		
		waves.draw(g2, player);
		islands.draw(g2, player, waves);
		
		grass.draw(g2, islands, player);
		
		try {
			for (Ball ball : balls) {
				ball.draw(g2, player.x, player.y);
			}
		} catch(Exception e) {
			
			
		}
		
		//updating bullets (drawing/update)
		for (Bullet b : bulletM.bullets) {
			b.draw(g2, player.x, player.y, X_OFFSET, Y_OFFSET);
		}
		player.draw(g2, X_OFFSET, Y_OFFSET);
		
		try {
			for (Zombie zombie : zombies) {
				
				zombie.draw(g2, player.x, player.y, X_OFFSET, Y_OFFSET);
				
			}
		} catch(Exception e) {
			
			System.out.println("Zombie doesn't exist anymore");
		}
		
		panel.draw(g2);
		
		//menu.draw(g2);
		
	}
	
	public void checkEvents() {
		
		if (keys.right) {
			
			player.move(player.speed, 0f, islands);
			//player.x+=player.speed;
			player.direction = 0;
			
		}
		if (keys.left) {
			
			player.move(-player.speed, 0f, islands);
			//player.x-=player.speed;
			player.direction = 2;
			
		}
		if (keys.up) {
		
			player.move(0f, -player.speed, islands);
			//player.y-=player.speed;
			player.direction = 3;
		
		}
		if (keys.down) {
		
			player.move(0f, player.speed, islands);
			//player.y+=player.speed;
			player.direction = 1;
		
		}
		
		if (!keys.down && !keys.up && !keys.left && !keys.right) {
			
			player.velX = 0;
			player.velY = 0;
			
		}
		
		if (mouse.clicked) {
			
			balls.add(new Ball(player.x + mouse.x - 30, player.y + mouse.y - 50, 50, new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255))));
		}
		
		
	}
	
	public void createBullet() {
		//making bullets
		float[] vels = getBulletVel(player.direction, bulletSpeed, player.velX, player.velY);
		
		bulletM.bullets.add(new Bullet((player.gun.finalX - X_OFFSET) + player.x + 4, (int) ((player.gun.finalY  - Y_OFFSET) + player.y + 4), vels[0], vels[1], 5, 100));
		
	}

	public void run() {
		
		while (running) {
			
			keys.main = main;
			
			repaint();
			
			checkEvents();
			
			bulletM.moveBullets(zombies);
			
			//making boolean assuming to clear balls
			boolean canDelete = true;
			
			//updating balls
			for (Ball ball : balls) {
				ball.update(islands, player.x, player.y);
				if (ball.lifeTime < 10) {
					//since a ball isn't ready to be deleted, don't delete balls
					canDelete = false;
				}
			}
			
			if (canDelete) {
				//if all balls are ready to be stopped, delete all balls
				balls.clear();
				
			}
			
			spawner.update(player, zombies);
			
			for (Zombie zombie : zombies) {
				
				zombie.makeMove(player, X_OFFSET, Y_OFFSET);
				
			}
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public float[] getBulletVel(int dir, float speed, float playerVelX, float playerVelY) {
		
		float[] arr = new float[2];
		
		if(dir == 0) {
			arr[0] = speed + playerVelX;
			arr[1] = playerVelY;
		} else if(dir == 1) {
			
			arr[0] = playerVelX;
			arr[1] = speed + playerVelY;
			
		} else if(dir == 2) {
			
			arr[0] = -speed + playerVelX;
			arr[1] = playerVelY;
			
		} else if (dir == 3) {
			
			arr[0] = playerVelX;
			arr[1] = -speed + playerVelY;
			
		}
		
		return arr;
	}

}
