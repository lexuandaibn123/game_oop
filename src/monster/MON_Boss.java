package monster;

import java.util.Random;

import data.progress;
import entity.Entity;
import entity.PlayerDummy;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Rock;

public class MON_Boss extends Entity {

	public static final String monName = "Skeleton Lord";

	GamePanel gp;

	public MON_Boss(GamePanel gp) {

		super(gp);

		this.gp = gp;

		type = type_monster;
		boss = true;
		name = monName;

		int size = gp.tileSize * 5;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 50;
		life = maxLife;
		attack = 10;
		defense = 2;
		exp = 50;
		mana = 0;
		knockBackPower = 5;
		sleep = true;

		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48 * 2;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		motion1_duration = 40;
		motion2_duration = 85;
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
		if (inRage == false) {
			up1 = setup("/monster/skeletonlord_up_1", gp.tileSize * 5, gp.tileSize * 5);
			up2 = setup("/monster/skeletonlord_up_2", gp.tileSize * 5, gp.tileSize * 5);
			down1 = setup("/monster/skeletonlord_down_1", gp.tileSize * 5, gp.tileSize * 5);
			down2 = setup("/monster/skeletonlord_down_2", gp.tileSize * 5, gp.tileSize * 5);
			right1 = setup("/monster/skeletonlord_right_1", gp.tileSize * 5, gp.tileSize * 5);
			right2 = setup("/monster/skeletonlord_right_2", gp.tileSize * 5, gp.tileSize * 5);
			left1 = setup("/monster/skeletonlord_left_1", gp.tileSize * 5, gp.tileSize * 5);
			left2 = setup("/monster/skeletonlord_left_2", gp.tileSize * 5, gp.tileSize * 5);
		}
		if (inRage == true) {
			up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize * 5, gp.tileSize * 5);
			up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize * 5, gp.tileSize * 5);
			down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize * 5, gp.tileSize * 5);
			down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize * 5, gp.tileSize * 5);
			right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize * 5, gp.tileSize * 5);
			right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize * 5, gp.tileSize * 5);
			left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize * 5, gp.tileSize * 5);
			left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize * 5, gp.tileSize * 5);
		}

	}

	public void getAttackImage() {
		if (inRage == false) {
			attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize * 5 * 2, gp.tileSize * 5);
			attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize * 5 * 2, gp.tileSize * 5);
			attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize * 5 * 2, gp.tileSize * 5);
			attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize * 5 * 2, gp.tileSize * 5);
		}
		if (inRage == true) {
			attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize * 5, gp.tileSize * 5 * 2);
			attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize * 5 * 2, gp.tileSize * 5);
			attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize * 5 * 2, gp.tileSize * 5);
			attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize * 5 * 2, gp.tileSize * 5);
			attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize * 5 * 2, gp.tileSize * 5);
		}
	}

	public void update() {

		super.update();

		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance) / gp.tileSize;

		if (onPath == false && tileDistance < 5) {

			int i = new Random().nextInt(100) + 1;
			if (i > 20) {
				onPath = true;
			}
		}
		if (onPath == true && tileDistance > 8) {
			onPath = false;
		}
	}

	public void setAction() {

		if (inRage == false && life < maxLife / 2) {
			inRage = true;
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack *= 2;
		}

		if (getTileDistance(gp.player) < 10) {
			moveTowardPlayer(60);
		} else {
			// get a random direction
			getRamdomDirection();
		}
		// check if it attacks
		if (attacking == false) {
			checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5);
		}

	}

	public void damageReaction() {

		actionLockCounter = 0;
		if (gp.player.keyH.shotKeyPressed == false) {
			generateParticle(gp.player.projectile, this, (gp.player.getAttack() - this.defense) + "");
		}

	}

	public void checkDrop() {

		// change mussic after boss die
		gp.bossBattleOn = false;
		progress.skeletonLordDefeated = true;
		gp.stopMusic();
		gp.playMusic(14);

		// hide the iron door
		for (int i = 0; i < gp.npc[1].length; i++) {
			// if (gp.npc[gp.currentMap][i] == null &&
			// gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
			gp.playSE(17);
			gp.obj[gp.currentMap][i] = null;
			// }
		}

		// CAST A DIE
		int i = new Random().nextInt(100) + 1;

		// SET THE MONSTER DROP
		if (i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if (i > 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if (i > 75 && i < 100) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
	}

}
