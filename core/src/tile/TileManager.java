package tile;

import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.mygdx.game.GamePanel;
import com.mygdx.game.SceneManager;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends SceneManager{
	
	public Tile[] tile;
	public int MapTileNum[][];
	
	
	// Constructor
	public TileManager(GamePanel gp) {
		super(gp);
		
		tile = new Tile[10];
		MapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/world02.txt");
	}
	
    // Getter and Setter for tile and MapTileNum
    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    public int[][] getMapTileNum() {
        return MapTileNum;
    }

    public void setMapTileNum(int[][] mapTileNum) {
        MapTileNum = mapTileNum;
    }

    // Rest of the TileManager class...

	
	// Creating a method to load the map
	public void loadMap(String filePath) {
		
		try{
			InputStream is = getClass().getResourceAsStream(filePath);
			// use this to read the file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();

				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");// split the line into numbers

					int num = Integer.parseInt(numbers[col]);// convert the number to an integer

					MapTileNum[col][row] = num;
					col++;
				}

				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}

			}
			br.close();

		} catch(Exception e) {

			e.printStackTrace();

		}
	}

	// Method to get the tile image
	public void getTileImage() {
		
		try {

			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));


		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// Create draw method to draw the tiles
	@Override
	public void draw(Graphics2D g2){
		
		int worldCol = 0;
		int worldRow = 0;
		

		while(worldCol <gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = MapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY +gp.player.screenY;

			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

			}

			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol =0;
				worldRow++;
			}

			
		}

	}
}
