package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {

	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);

		type = type_pickaxe;
		name = "Pickaxe";
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 3;
		description = "[" + name + "]\nYou will dig it!";
		attackArea.width = 30;
		attackArea.height = 30;
		price = 15;
		knockBackPower = 8;
		motion1_duration = 10;
		motion2_duration = 20;
	}

}
