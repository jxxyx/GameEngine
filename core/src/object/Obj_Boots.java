package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Boots extends SuperObject {
    // Create a constructor
    public Obj_Boots() {

        name = "Boots";
        type = type_object;
        description = "[" + name + "]\nA boot that will increase your speed.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
