package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_27 extends SuperObject{
    // Create a constructor
    public Obj_27(){

        name = "Answer 27";
        value = 27;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_27.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
