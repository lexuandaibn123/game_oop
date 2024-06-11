package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Rock;

public class MON_Orc extends Entity{

	GamePanel gp;
public MON_Orc(GamePanel gp) {
		
		
		super(gp);
		 
		this.gp = gp;
		
		type = type_monster;
		name = "Orc";
		
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 3;
		life = maxLife;
		attack = 2;
		defense = 1;
		exp = 4;
		mana = 0;
		
		
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 44;
		solidAreaDefaultX = solidArea.x ;
		solidAreaDefaultY = solidArea.y;
		attackArea.width=48;
		attackArea.height=48;
		motion1_duration=40;
		motion2_duration=85;
		getImage();
		getAttackImage();
	
	}
	
	public void setUpdate() {
		maxLife++;
		life = maxLife;
		exp++;
		attack++;
		defense++;
	}
	
	public void getImage() {
		
		up1 = setup("/monster/orc_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/orc_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/orc_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/orc_right_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/orc_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/orc_left_2", gp.tileSize, gp.tileSize);
	}
	
public void getAttackImage() {
		
	attackUp1 = setup("/monster/orc_attack_up_1", gp.tileSize*5, gp.tileSize*5);
	attackUp2 = setup("/monster/orc_attack_up_2", gp.tileSize*5, gp.tileSize*5);
	attackDown1 = setup("/monster/orc_attack_down_1", gp.tileSize*5, gp.tileSize*5);
	attackDown2 = setup("/monster/orc_attack_down_2", gp.tileSize*5, gp.tileSize*5);
	attackLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize*5, gp.tileSize*5);
	attackLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize*5, gp.tileSize*5);
	attackRight1 = setup("/monster/orc_attack_right_1", gp.tileSize*5, gp.tileSize*5);
	attackRight2 = setup("/monster/orc_attack_right_2", gp.tileSize*5, gp.tileSize*5);
	
	}
public void update() {
	
	super.update();
	
	int xDistance = Math.abs(worldX - gp.player.worldX);
	int yDistance = Math.abs(worldY - gp.player.worldY);
	int tileDistance = (xDistance + yDistance)/gp.tileSize;
	
	if(onPath == false && tileDistance < 5)
	{
		
		int i = new Random().nextInt(100) + 1;
		if(i>20) {
			onPath  = true;
		}
	}
	if(onPath == true && tileDistance > 8) {
		onPath = false;
	}
}
	public void setAction() {
		
		if(onPath==true) {
			//check if it stops chasing
			checkStopChasingOrNot(gp.player,15,100);
			
			//search the direction to go
    		searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
    		
		}
		else {
			//check if it starts chasing
			checkStartChasingOrNot(gp.player,5,100);
		
			//get a random direction
		getRamdomDirection();
		}	
	    	/*int i = new Random().nextInt(100) + 1;
	    	if(i > 99 && projectile.alive == false && shotAvailableCounter == 15) {
	    		
	    		projectile.set(worldX, worldY, direction, true, this);
	    		

	    		// CHECK VACACY
	    		for(int j = 0; j< gp.projectile[gp.currentMap].length; j++) {
	    			if(gp.projectile[gp.currentMap][j] == null) {
	    				gp.projectile[gp.currentMap][j] = projectile;
	    				break;
	    			}
	    		}
	    		
	    		
	    		shotAvailableCounter = 0;
	    		
	    	}*/
	 //check if it attacks
	    if(attacking==false) {
	    	checkAttackOrNot(30,gp.tileSize*4,gp.tileSize);
	    }
		
		}
		
	
	public void damageReaction() {
		
		actionLockCounter = 0;
		if(gp.player.keyH.shotKeyPressed == false) {
			generateParticle(gp.player.projectile, this, (gp.player.getAttack() - this.defense) + "");
		}
	
		onPath = true;
	}
	
	public void checkDrop() {
		
		// CAST A DIE
		int i = new Random().nextInt(100) + 1;
		
		// SET THE MONSTER DROP
		if(i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i > 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i > 75 && i < 100) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
	}
	
	

}
