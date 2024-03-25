package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_56 extends SuperObject {
    // Create a constructor
    public Obj_56(){

        name = "Answer 56";
        value = 56;
        type = type_number;
        description = "[" + name + "]\nA solution to a question.";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/number_56.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
