package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {

	GamePanel gp;
	public static final String objName = "Blue Heart";

	public OBJ_BlueHeart(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_pickupOnly;
		name = objName;

		down1 = setup("/player/dragon_down", gp.tileSize * 2, gp.tileSize * 2);
	}

	public void use(Entity entity) {
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
	}

}
