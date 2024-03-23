package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_5 extends SuperObject {
    // Create a constructor
    public Obj_5(){

        name = "Answer 5";
        value = 5;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_5.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

