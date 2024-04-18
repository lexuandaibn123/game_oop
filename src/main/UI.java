package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {			//Giao diện
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	//BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public int slotCol = 0;
	public int slotRow = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
//		OBJ_Key key = new OBJ_Key();
//		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			//
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		//DIALOGUE STATE
		//CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawInventory();
		}
			
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		
		g2.drawString(text, x, y);
		
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
		
	}
	
	public void drawInventory() {  						//Túi đồ
		
		//FRAME
		int frameX = gp.tileSize*12;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		
		//DRAW PLAYER'S ITEMS
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			
			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
			slotX += gp.tileSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
		}
		
		
		//CURSOR
		int cursorX = slotXstart + (gp.tileSize * slotCol);
		int cursorY = slotYstart + (gp.tileSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		
		//DRAW CURSOR
		g2.setColor(Color.white);
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+5, y+5, width, height, 35, 35);
		
		
	}
}
