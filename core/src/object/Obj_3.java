package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_3 extends SuperObject {
    // Create a constructor
    public Obj_3(){

        name = "Answer 3";
        value = 3;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_3.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
