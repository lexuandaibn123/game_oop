package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI { // Giao diện

	GamePanel gp;
	Graphics2D g2;
	Font Arial_40, Arial_80B;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
  
	public int commandNum=0;
	public int titleScreen=0;//0:titleScreen1 ; 1:titleScreen2 ; 2:titleScreen3

	public int slotCol = 0;
	public int slotRow = 0;

	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		Arial_40 = new Font("Arial", Font.PLAIN, 40);
		Arial_80B = new Font("Arial", Font.BOLD, 80);
		// OBJ_Key key = new OBJ_Key();
		// keyImage = key.image;

	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(Arial_40);
		g2.setColor(Color.white);

		//TITLE STATE
		if(gp.gameState==gp.titleState){
			drawTitleScreen();

		}
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			//
		}
		// PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// DIALOGUE STATE
		// CHARACTER STATE
		if (gp.gameState == gp.characterState) {
			drawInventory();
			drawCharacterStatus();
		}

	}

	public void drawTitleScreen()
	{
		if(titleScreen==0){
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
			//TITLE SCREEN
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text="Blue Boy Adventure";
			int x=getXforCenteredText(text);
			int y= gp.tileSize*3;
			//SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text,x+5,y+5);
			//MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text,x,y);
			// PLAY BOY IMAGE
			x=gp.screenWidth/2-gp.tileSize*2/2;
			y+= gp.tileSize*2;
			g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			text="New Game";
			x=getXforCenteredText(text);
			y+=gp.tileSize*4;
			g2.drawString(text,x,y);
			if(commandNum==0){
				g2.drawString(">",x-gp.tileSize,y);
			}

			text="Load Game";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1){
				g2.drawString(">",x-gp.tileSize,y);
			}

			text="Quit";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2){
				g2.drawString(">",x-gp.tileSize,y);
			}
		}
		else if(titleScreen==1){
			//CLASS SELECTION SCREEN
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			String text="Select Your class";
			int x=getXforCenteredText(text);
			int y=gp.tileSize*3;
			g2.drawString(text,x,y);

			text="Fighter";
			x=getXforCenteredText(text);
			y+=gp.tileSize*3;
			g2.drawString(text,x,y);
			if(commandNum==0){
				g2.drawString(">",x- gp.tileSize,y);
			}

			text="Thief";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1){
				g2.drawString(">",x-gp.tileSize,y);
			}
			text="Sorcerer";
			x=getXforCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2){
				g2.drawString(">",x-gp.tileSize,y);
			}
			text="Back";
			x=getXforCenteredText(text);
			y+=gp.tileSize*3;
			g2.drawString(text,x,y);
			if(commandNum==3){
				g2.drawString(">",x-gp.tileSize,y);
			}// T la dat
		}
	}
	
	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);

	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;

	}

	public void drawCharacterStatus() {
		int frameX = gp.tileSize * 2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 7;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		// name
		g2.drawString("Level", textX, textY);
		textY += 40;
		g2.drawString("Exp", textX, textY);
		textY += 40;
		g2.drawString("Next Level", textX, textY);
		textY += 40;
		g2.drawString("Life", textX, textY);
		textY += 40;
		g2.drawString("Strength", textX, textY);
		textY += 40;
		g2.drawString("Dexterity", textX, textY);
		textY += 40;
		g2.drawString("Coin", textX, textY);
		textY += 40;
		g2.drawString("Attack", textX, textY);
		textY += 40;
		g2.drawString("Defense", textX, textY);
		textY += 40;
		// value
		int tallX = (frameX + frameWidth) - 30;
		textY = frameY + gp.tileSize;
		String value;
		value = String.valueOf(gp.player.level);
		textX = layXbenphai(value, tallX);
		g2.drawString(value, textX, textY);
		textY += 40;
		value = String.valueOf(gp.player.exp);
		textX = layXbenphai(value, tallX);
		g2.drawString(value, textX, textY);
		textY += 40;
		value = String.valueOf(gp.player.nextLevelExp);
		textX = layXbenphai(value, tallX);
		g2.drawString(value, textX, textY);
		textY += 40;
		// value = String.valueOf(gp.player.level);
		// textX = layXbenphai(value, tallX);
		// g2.drawString(value, textX, textY);
		textY += 40;
		value = String.valueOf(gp.player.strength);
		textX = layXbenphai(value, tallX);
		g2.drawString(value, textX, textY);
		textY += 40;
		value = String.valueOf(gp.player.dexterity);
		textX = layXbenphai(value, tallX);
		g2.drawString(value, textX, textY);
	}

	public int layXbenphai(String text, int tallX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tallX - length;
		return x;
	}

	public void drawInventory() { // Túi đồ

		// FRAME
		int frameX = gp.tileSize * 12;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;

		// DRAW PLAYER'S ITEMS
		for (int i = 0; i < gp.player.inventory.size(); i++) {

			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
			slotX += gp.tileSize;

			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
		}

		// CURSOR
		int cursorX = slotXstart + (gp.tileSize * slotCol);
		int cursorY = slotYstart + (gp.tileSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		// DRAW CURSOR
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
		g2.drawRoundRect(x + 5, y + 5, width, height, 35, 35);

	}
}
