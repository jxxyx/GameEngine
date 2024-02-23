package object;

import java.io.IOException;
// import java.io.InvalidObjectException; 
import javax.imageio.ImageIO;

public class Obj_Boots extends SuperObject {
    // Create a constructor
    public Obj_Boots() {

        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
