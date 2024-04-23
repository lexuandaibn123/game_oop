package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// CÀI ĐẶT CỬA SỔ HIỂN THỊ 
	final int originalTitleSize = 16; //16x16 
	final int scale = 3; // Tỉ lệ
	
	public final int tileSize = originalTitleSize * scale; //48x48
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //CD : 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //CR : 576 pixels
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//public final int worldWidth = tileSize * maxScreenCol;
	//public final int worldHeight = tileSize * maxScreenRow;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandlers keyH = new KeyHandlers(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this); //Đưa object lên bản đồ
	public UI ui = new UI(this);
	Thread gameThread;
	
	//ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	
	public SuperObject obj[] = new SuperObject[20];    //Tạo vị trí cho object
	
	//GAME STATE
	public int gameState;


	public final int titleState=0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int characterState = 3;

	
	
	//Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		
		playMusic(0);
		stopMusic();
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		
		}
	}
	public void update() {
		if(gameState == playState) {
			player.update();
		}
		if(gameState == pauseState) {
			//nothing
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		

		// TITLE Screen
		if(gameState==titleState)
		{
			ui.draw(g2);
		}
		else{//TILE
			tileM.draw(g2);

			//OBJECT
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}

			//PLAYER
			player.draw(g2);

			//UI
			ui.draw(g2);
			//OBJECT
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}

			//PLAYER
			player.draw(g2);

			//UI
			ui.draw(g2);

			g2.dispose();
		}
		//
		


	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
}
