package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlers implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	boolean checkDrawTime = false;
	
	public KeyHandlers(GamePanel gp) {
		this.gp = gp;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();

		if(gp.gameState==gp.titleState){
			if(gp.ui.titleScreen==0){
				if(code==KeyEvent.VK_W){
					gp.ui.commandNum--;
					if(gp.ui.commandNum<0){
						gp.ui.commandNum=2;
					}
				}

				if(code==KeyEvent.VK_S){
					gp.ui.commandNum++;
					if(gp.ui.commandNum>2){
						gp.ui.commandNum=0;
					}
				}
				if(code==KeyEvent.VK_ENTER){
					if(gp.ui.commandNum==0){
						gp.ui.titleScreen=1;
						gp.playMusic(0);
					}
					if(gp.ui.commandNum==1){
						//add later
					}
					if(gp.ui.commandNum==2){
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreen==1)
			{
				if(code==KeyEvent.VK_W){
					gp.ui.commandNum--;
					if(gp.ui.commandNum<0){
						gp.ui.commandNum=3;
					}
				}

				if(code==KeyEvent.VK_S){
					gp.ui.commandNum++;
					if(gp.ui.commandNum>3){
						gp.ui.commandNum=0;
					}
				}
				if(code==KeyEvent.VK_ENTER){
					if(gp.ui.commandNum==0){
						System.out.println("Do some fighter specific stuff");
						gp.gameState=gp.playState;
						gp.playMusic(0);
					}

					if(gp.ui.commandNum==1){
						System.out.println("Do some thief specific stuff");
						gp.gameState=gp.playState;
						gp.playMusic(0);
					}

					if(gp.ui.commandNum==2){
						System.out.println("Do some socerer specific stuff");
						gp.gameState=gp.playState;
						gp.playMusic(0);
					}

					if(gp.ui.commandNum==3){
						gp.ui.titleScreen=0;
						gp.ui.commandNum=0;
					}
				}

			}

		}
	
		/*if(gameState == gp.titleState) {
			titleState(code);
		}*/
		if(gp.gameState == gp.playState) {
			playState(code);
		}
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
	}
	
	public void playState(int code) {
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
				gp.gameState = gp.characterState;
		}
	}
	
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code) {
		
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if(gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if(gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
		
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
			}
		}
	}

}
