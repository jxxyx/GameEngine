package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_key extends SuperObject{
    
    // Create a constructor
    public Obj_key(){

        name = "Key";
        description = "[" + name + "]\nAn key to unlock doors.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
