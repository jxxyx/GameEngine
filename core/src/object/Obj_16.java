package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_16 extends SuperObject{
    // Create a constructor
    public Obj_16(){

        name = "Answer 16";
        value = 16;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_16.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
