package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_8 extends SuperObject {
    // Create a constructor
    public Obj_8(){

        name = "Answer 8";
        value = 8;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_8.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

