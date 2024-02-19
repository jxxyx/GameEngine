package entity;

import com.mygdx.game.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int MapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
	}
	
	public void getTileImage() {
		//add this to the below of their tile initialization
		//tile[1].collision = true;
		//tile[2].collision = true;
		//tile[4].collision = true;
	}
}
