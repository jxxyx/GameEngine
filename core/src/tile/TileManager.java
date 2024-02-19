package tile;

import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.mygdx.game.GamePanel;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int MapTileNum[][];
	
	
	// Contructor
	public TileManager(GamePanel gp) {
		
		this.gp = gp;

		tile = new Tile[10];
		MapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	// Creating a method to load the map
	public void loadMap(String filePath) {
		
		try{
			InputStream is = getClass().getResourceAsStream(filePath);
			// use this to read the file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine();

				while(col < gp.maxScreenCol) {
					
					String numbers[] = line.split(" ");// split the line into numbers

					int num = Integer.parseInt(numbers[col]);// convert the number to an integer

					MapTileNum[col][row] = num;
					col++;
				}

				if(col == gp.maxScreenCol) {
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

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// Create draw method to draw the tiles
	public void draw(Graphics2D g2){
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y =0;

		while(col <gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = MapTileNum[col][row];

			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;

			if(col == gp.maxScreenCol) {
				col =0;
				x = 0;
				row++;
				y += gp.tileSize;
			}

			
		}

	}
}
