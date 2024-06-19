package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Iron extends Entity {

	public OBJ_Door_Iron(GamePanel gp) {
		super(gp);
		name = "iron door";
		down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize);

		type = type_door_iron;

		collision = true;

		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
