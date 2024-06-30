
package entity;

import main.GamePanel;

public class PlayerDummy extends Entity {

	public static final String npcName = "Dummy";

	public PlayerDummy(GamePanel gp) {
		super(gp);

		name = npcName;
		getImage();
	}

	public void getImage() {
		up1 = setup("/player/up1", gp.tileSize * 5, gp.tileSize * 5);
		up2 = setup("/player/up2", gp.tileSize * 5, gp.tileSize * 5);
		down1 = setup("/player/down1", 5 * gp.tileSize, 5 * gp.tileSize);
		down2 = setup("/player/down2", gp.tileSize * 5, 5 * gp.tileSize);
		left1 = setup("/player/left1", gp.tileSize * 5, gp.tileSize * 5);
		left2 = setup("/player/left2", gp.tileSize * 5, gp.tileSize * 5);
		right1 = setup("/player/right2", gp.tileSize * 5, gp.tileSize * 5);
		right2 = setup("/player/right1", gp.tileSize * 5, gp.tileSize * 5);
	}
}