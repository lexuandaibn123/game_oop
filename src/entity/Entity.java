
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Sword_Normal;


public class Entity {
	
	GamePanel gp;
  
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
    attackLeft1, attackLeft2, attackRight1, attackRight2,guardUp,guardDown,guardLeft,guardRight;
	public BufferedImage image, image2, image3 ; 
    public Rectangle solidArea = new Rectangle( 0 , 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public String dialogues[][] = new String[10][20];
    public Entity attacker;

    // STATE
    public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	public int dialogueIndex = 0;
	public boolean collision = false;
    public boolean invincible = false;
  public  boolean attacking = false;
    public boolean alive = true;
    public boolean dying =false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding=false;
	// COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
	int knockBackCounter = 0;
    
    // CHARACTER STATUS

    public String name;
    public int defaultSpeed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int speed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public static int NVcounter= 0;

    // ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost=0;
    public int price;
    public boolean stackable = false;
    public int amount = 1;
    public int knockBackPower = 0;
    
    // TYPE 
    
    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_door = 8;
    public final int type_chest = 9;
    public final int type_monster_die = 10;
    
    
    public Entity(GamePanel gp) {
    	
    	this.gp = gp;
    	
    }
    
    public int getWorldX() {
    	return worldX;
    	
    }
    public int getWorldY() {
    	return worldY;
    }
    
    public void use(Entity entity) {
    	
    }
    
    public void setUpdate() {
    	
    }
    
    public void checkDrop() {
    	
    }
    public void dropItem(Entity droppedItem ) {

    	
    	for(int i =0; i< gp.obj[gp.currentMap].length; i++) {
    		if(gp.obj[gp.currentMap][i] == null) {
    			gp.obj[gp.currentMap][i] = droppedItem;
    			gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster's worldX
    			gp.obj[gp.currentMap][i].worldY = worldY; // the dead monster's worldY
    			break;
    		}
    	}
    }
    
	
	public Color getParticleColor() {
		Color color = null;
		return color;
	}
	public int getParticleSize() {
		int size = 0; // 6 pixels;
		return size;
	}
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
    
	public void generateParticle(Entity generator, Entity target, String mes) {
		
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -1, -1,mes);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, -1, +1, mes);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, +1, -1,mes);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, +1, +1,mes);
		
		int i = new Random().nextInt(100) + 1;
		
		if(0 < i && i<=25) {
			gp.particleList.add(p1);
		}
		else if(25<i && i<=50) {
			gp.particleList.add(p2);
		}
		else if(50<i && i<=75) {
			gp.particleList.add(p3);
		}
		else if(i>75 && i<=100) {
			gp.particleList.add(p4);
		}
		
		
	}
	public int getXdistance(Entity target) {
		int xDistance = Math.abs(worldX - target.worldX);
	return xDistance;
	}
	public int getYdistance(Entity target) {
		int yDistance = Math.abs(worldY - target.worldY);
	return yDistance;
	}
	public int getTileDistance(Entity target) {
		int tileDistance =(getXdistance(target) +getYdistance(target))/gp.tileSize;
	return tileDistance;
	}
	public int getGoalCol(Entity target) {
		int goalCol=(target.worldX+target.solidArea.x)/gp.tileSize;
		return goalCol;
	}
	public int getGoalRow(Entity target) {
		int goalRow=(target.worldY+target.solidArea.y)/gp.tileSize;
		return goalRow;
	}
    public void setAction() { }
    public void damageReaction() {
    	
    }
    
    public void speak() {

    	
    	if(dialogues[NVcounter][dialogueIndex] == null) {
    		dialogueIndex = 0;
//    		gp.gameState = gp.playState;
    	}
    	gp.ui.currentDialogue = dialogues[NVcounter][dialogueIndex];
    	dialogueIndex ++;
    	
    	switch(gp.player.direction) {
    	case "up":
    		direction = "down";
    		break;
    	case "down":
    		direction = "up";
    		break;
    	case "right":
    		direction = "left";
    		break;
    	case "left":
    		direction = "right";
    	}
    
    	
    }
    
    public void checkCollision() {
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	gp.cChecker.checkObject(this, false);
    	gp.cChecker.checkEntity(this, gp.npc);
    	gp.cChecker.checkEntity(this, gp.monster);
    	gp.cChecker.checkEntity(this, gp.iTile);
    	boolean contactPlayer =  gp.cChecker.checkPlayer(this);
    	
    	
    	if(this.type == type_monster && contactPlayer == true) {
    		
    		damagePlayer(attack);
    		this.onPath = false;
    		
    	}
    }
    
    public void update() {
    	
    	
    	if(knockBack == true) {
    		checkCollision();
    		
    		if(collisionOn == true) {
    			
    			knockBackCounter = 0;
    			knockBack = false;
    			speed = defaultSpeed;
    		}
    		else 
    		{
    			switch(knockBackDirection) {
    			
    		   	case "up": worldY -= speed; break;
            	case "down": worldY += speed; break;
            	case "right": worldX += speed; break;
            	case "left": worldX -= speed; break;
    			
    			}
    		}
    			
    		knockBackCounter++;
    		if(knockBackCounter == 10) {
    			knockBackCounter = 0;
    			knockBack = false;
    			speed = defaultSpeed;
    		}
    		
    	}
    	else if (attacking==true) {
    		attacking();
    	}
    	else {
    	
    	
    	setAction();
    	checkCollision();
    		
		// IF COLLISION IS FALSE PLAYER CAN MOVE
        if(collisionOn == false) {
        	
        	switch(direction){
        	
        	case "up": worldY -= speed; break;
        	case "down": worldY += speed; break;
        	case "right": worldX += speed; break;
        	case "left": worldX -= speed; break;
        	
        	}
        	  spriteCounter++;
              if(spriteCounter > 24){
                      if(spriteNum ==1){
                          spriteNum=2;
                          
                      }
                      else if(spriteNum == 2){
                          spriteNum = 1;
                      }
                      spriteCounter = 0;
              	}
              
        }
        
        
      
        
    	if(invincible == true) {
    		invincibleCounter ++;
    		if(invincibleCounter > 10) {
    			invincible = false;
    			invincibleCounter = 0;
    		}
    	}
    	if(shotAvailableCounter < 15) {
    		shotAvailableCounter++;
    	}
    }
    } 
    public void checkAttackOrNot(int rate,int straight,int holizontal) {
    	boolean targetInRange=false;
    	int xDis=getXdistance(gp.player);
    	int yDis=getYdistance(gp.player);
    	switch(direction) {
    	case "up" :
    		if(gp.player.worldY<worldY&&yDis<straight&&xDis<holizontal)
    			{targetInRange=true;}
    		break;
    	case "down" :
    		if(gp.player.worldY>worldY&&yDis<straight&&xDis<holizontal)
			{targetInRange=true;}
    		break;
    	case "left" :
    		if(gp.player.worldX<worldX&&xDis<straight&&yDis<holizontal)
			{targetInRange=true;}
    		break;
    	case "right" :
    		if(gp.player.worldX>worldX&&xDis<straight&&yDis<holizontal)
			{targetInRange=true;}
    		break;
    		
    	}
    	if(targetInRange==true) {
    		//check if it initiates attack
    		int i=new Random().nextInt(rate);
    		if(i==0) {
    			attacking=true;
    			spriteNum=1;
    			spriteCounter=0;
    			shotAvailableCounter=0;
    		}
    	}
    }
    public void checkShootOrNot(int rate,int shootInterval) {
    	int i = new Random().nextInt(rate) + 1;
		if(i == 0 && projectile.alive == false && shotAvailableCounter == shootInterval) {
			
			projectile.set(worldX, worldY, direction, true, this);
			
    		// CHECK VACACY
    		for(int j = 0; j< gp.projectile[gp.currentMap].length; j++) {
    			if(gp.projectile[gp.currentMap][j] == null) {
    				gp.projectile[gp.currentMap][j] = projectile;
    				break;
    			}
    		}
			shotAvailableCounter  = 0;
		}
		
    }
    public void checkStartChasingOrNot(Entity target,int distance,int rate) {
    	if(getTileDistance(target)<distance) {
    		int i=new Random().nextInt(rate);
    		if(i==0) {
    			onPath=true;
    		}
    	}
    }
    public void checkStopChasingOrNot(Entity target,int distance,int rate) {
    	if(getTileDistance(target)>distance) {
    		int i=new Random().nextInt(rate);
    		if(i==0) {
    			onPath=false;
    		}
    	}
    }
    public void getRamdomDirection() {
actionLockCounter ++;
    	
    	if(actionLockCounter == 120) {
    	   	
        	Random random = new Random();
        	int i = random.nextInt(100) + 1; //pick up a number from 1 to 100
        	
        	if( i <= 25) {
        		direction = "up";
        	}
        	if(i > 25 && i<= 50) {
        		direction = "down";
        	}
        	if(i> 50 && i<=75) {
        		direction = "right";
        	}
        	if(i>75 && i<=100) {
        		direction = "left";
        	}
        	
        	actionLockCounter = 0;
        	}
		
    }
 public void attacking() {
    	
    	spriteCounter ++;
    	
    	if(spriteCounter <= motion1_duration) {
    		spriteNum = 1;
    	}
    	if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
    		spriteNum = 2;
    		
    		// Save the current worldX, worldY, solidArea
    		int currentWorldX = worldX;
    		int currentWorldY =worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		
    		// Adjust player's worldX, Y for the attackArea
    		switch(direction) {
    		case "up": worldY -= attackArea.height; break;
    		case "down": worldY += attackArea.height; break;
    		case "right": worldX += attackArea.width; break;
    		case "left": worldX -= attackArea.width; break;
    		}
    		
    		// attackArea becomes solidArea
    		solidArea.width = solidAreaWidth;
    		solidArea.height = solidAreaHeight;
    		if(type==type_monster) {
    			if(gp.cChecker.checkPlayer(this)==true) {
    				damagePlayer(attack);
    			}
    		}
    		else {//Player
    			// check monster collision with the updated worldX, worldY, and solidArea
        		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        		gp.player.damageMonster(monsterIndex,this, attack, currentWeapon.knockBackPower);
        		
        		
        		int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
        		gp.player.damageInteractiveTile(iTileIndex);
        		
        		int projectTileIndex = gp.cChecker.checkEntity(this, gp.projectile);
        		gp.player.damageProjectTile(projectTileIndex);
        			
    		}
    	
    		// After checking collision, restore the original data
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth;
    		solidArea.height = solidAreaHeight;
    		
    	}
    	if(spriteCounter > motion2_duration) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }
    
    public void damagePlayer(int attack) {

		if(gp.player.invincible == false) {
			// we can give damage
			if(gp.player.keyH.shotKeyPressed == false) {
				gp.playSE(6);
			}
			
			
			int damage = attack - gp.player.defense;
			if(damage < 0) damage = 0;
			
			gp.player.life -= damage;
			gp.player.invincible = true;
			
		}
		
	
    }
   public void setKnockBack(Entity taget,Entity attacker, int knockBackPower) {
    	this.attacker=attacker;
    	taget.knockBackDirection=attacker.direction;
    	taget.speed += knockBackPower;
    	taget.knockBack = true;
    	
    	
    }
    public void draw(Graphics2D g2) {
    	
    	
    	BufferedImage image = null;
    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
    	int screenY = worldY - gp.player.worldY + gp.player.screenY;
    	
        int tempScreenX = screenX;
        int tempScreenY = screenY;
    	
        if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        	worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        	worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        	worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        	

        	
            switch(direction){
            case "up":
                 if(spriteNum == 1){image = up1;}
                 if(spriteNum==2){ image = up2;  }
                   break;
            case "down":
                 if(spriteNum == 1){
              	  image = down1;}
                 if(spriteNum==2){
                    image = down2;
                       }
                   break;
            case "left":    
                 if(spriteNum == 1){
                    image = left1;}
                 if(spriteNum==2){
                    image = left2;
                       }
                   break;
            case "right":
                 if(spriteNum == 1){
                    image = right1;}
                 if(spriteNum==2){
                    image = right2;
                        }
                   break;
            	}//end switch
        	

            int x = tempScreenX;
            int y = tempScreenY;
            if(tempScreenX > worldX) {
          	  x = worldX;
            }
            if(tempScreenY > worldY) {
          	  	y = worldY;
            }
            int rightOffset = gp.screenWidth - tempScreenX;
            if(rightOffset > gp.worldWidth - worldX) {
        			x = gp.screenWidth - (gp.worldWidth - worldX);
        	}
            int bottomOffset = gp.screenHeight - tempScreenY;
            if(bottomOffset > gp.worldHeight - worldY) {
          	  	y = gp.screenHeight - (gp.worldHeight - worldY);
        	}
            
            
            // Monster HP bar
            if(type == type_monster && hpBarOn == true) {
            	
            	double oneScale = (double)gp.tileSize / maxLife;
            	double hpBarValue = oneScale * life;
            	
            	g2.setColor(new Color(35,35,35));
            	g2.fillRect(x- 1, y- 16, gp.tileSize+2, 12);
            	
            	g2.setColor(new Color(255,0,30));
                g2.fillRect(x, y- 15, (int)hpBarValue, 10);
                
                hpBarCounter++;
                
                if(hpBarCounter > 200) {
                	
                	life = maxLife;
                	hpBarCounter = 0;
                	hpBarOn = false;
                }
            }
           
            
            
            if(invincible == true) {
            	hpBarOn = true;
            	hpBarCounter = 0;
            	changeAlpha(g2, 0.4f);
            }
            if(dying == true) {
            	
            	dyingAnimation(g2);
            }
            
            g2.drawImage(image, x, y, null);
            
            // Reset alpha
            changeAlpha(g2, 1f);
            
        	
        	
        }
		
	
    	
    }
    
    public void dyingAnimation(Graphics2D g2) {
    	
    	dyingCounter++;
    	
    	int i = 5;
    	
    	if(dyingCounter <= i) {changeAlpha(g2, 0f);	}

    	if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}	
    	if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
    	if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
    	if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
    	if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
    	if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
    	if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
    	if(dyingCounter > i*8) {
    		alive = false;
    	}
    }
    
    
    public void changeAlpha(Graphics2D g2, float alphaValue) {
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    
    public BufferedImage setup(String imagePath, int width, int height) {
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	
    	try {
    		image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
    		image = uTool.scaleImage(image, width, height);
    		
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return image;
    }
    
    public void searchPath(int goalCol, int goalRow) {
    	
    	int startCol = (worldX + solidArea.x)/gp.tileSize;
    	int startRow = (worldY + solidArea.y)/gp.tileSize;
    	
    	gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
    	
    	if(gp.pFinder.search() == true) {
    		
    		// Next worldX & worldY
    		int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
    		int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
    		// Entity's solidArea position
    		int enLeftX = worldX + solidArea.x;
    		int enRightX = worldX + solidArea.x + solidArea.width;
    		int enTopY = worldY + solidArea.y;
    		int enBottomY = worldY + solidArea.y + solidArea.height;
    		
    		if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
    			direction = "up";
    		}
    		else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
    			direction = "down";
    		}
    		else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
    			//left or right
    			if(enLeftX > nextX) {
    				direction = "left";
    			}
    			if(enLeftX < nextX) {
    				direction = "right";
    			}
    			
    		}
    		else if(enTopY > nextY && enLeftX > nextX) {
    			// up or left
    			direction = "up";
    			checkCollision();
    			if(collisionOn == true) {
    				direction = "left";
    			}
    		}
    		else if(enTopY > nextY && enLeftX < nextX) {
    			// up or right
    			direction = "up";
    			checkCollision();
    			if(collisionOn== true) {
    				direction = "right";
    			}
    		}
    		else if(enTopY < nextY && enLeftX > nextX) {
    			// down or left
    			direction = "down";
    			checkCollision();
    			if(collisionOn == true) {
    				direction = "left";
    			}
    		}
    		else if(enTopY < nextY && enLeftX < nextX) {
    			// down or right
    			direction = "down";
    			checkCollision();
    			if(collisionOn == true) {
    				direction = "right";
    			}
    		}
    		
    		// If reaches the goal, stop the search
    		int nextCol = gp.pFinder.pathList.get(0).col;
    		int nextRow= gp.pFinder.pathList.get(0).row;
    		if(nextCol == goalCol && nextRow == goalRow) {
    			onPath = false;
    		}
    	}
    }
    
}
