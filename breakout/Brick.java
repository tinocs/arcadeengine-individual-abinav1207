package breakout;

import engine.Actor;
import javafx.scene.image.Image;

/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class Brick extends Actor {

    
    public Brick() {
        String path = getClass().getClassLoader()
                .getResource("breakoutresources/brick.png").toString();
        setImage(new Image(path));
    }

    @Override
    public void act(long now) {
    }
}