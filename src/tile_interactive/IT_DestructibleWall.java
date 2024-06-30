package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_DestructibleWall extends InteractiveTile {

	GamePanel gp;

	public IT_DestructibleWall(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		life = 3;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;

		down1 = setup("/tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
		down2 = setup("/tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
		destructible = true;
		defense = 2;
	}

	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;

		if (entity.currentWeapon.type == type_pickaxe) {
			isCorrectItem = true;
		}

		return isCorrectItem;
	}

	public void playSE() {
		gp.playSE(11);
	}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}

	public Color getParticleColor() {
		Color color = new Color(65, 65, 65);
		return color;
	}

	public int getParticleSize() {
		int size = 6; // 6 pixels;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}

	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}
