package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_420 extends SuperObject {
    // Create a constructor
    public Obj_420(){

        name = "Answer 420";
        value = 420;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_420.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
