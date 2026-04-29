package breakout;

import engine.Actor;
import javafx.scene.image.Image;

/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class Brick extends Actor {
private char type;
    
    public Brick(char type) {
        this.type = type;
        if(type=='1'){
        String path = getClass().getClassLoader()
                .getResource("breakoutresources/brick.png").toString();
        setImage(new Image(path));
    }else if(type=='2'){
        String path = getClass().getClassLoader()
                .getResource("breakoutresources/brick2.png").toString();
        setImage(new Image(path));
    }
}
public char getType(){
    return type;
}

    @Override
    public void act(long now) {
    }
}