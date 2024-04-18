package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {                            //Setup vị trí của các objects
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 17 * gp.tileSize;
		gp.obj[0].worldY = 11 * gp.tileSize;
	
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 2 * gp.tileSize;
		gp.obj[1].worldY = 2 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 3 * gp.tileSize;
		gp.obj[2].worldY = 3 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 16 * gp.tileSize;
		gp.obj[3].worldY = 17 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 5 * gp.tileSize;
		gp.obj[4].worldY = 5 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Chest();
		gp.obj[5].worldX = 17 * gp.tileSize;
		gp.obj[5].worldY = 11 * gp.tileSize;
		
	/*	gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 7 * gp.tileSize;
		gp.obj[6].worldY = 7 * gp.tileSize;   */
		
		gp.obj[7] = new OBJ_Boots();
		gp.obj[7].worldX = 10 * gp.tileSize;
		gp.obj[7].worldY = 2 * gp.tileSize;
	
	
	}
}
