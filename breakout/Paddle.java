package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class Paddle extends Actor {

    private static final double SPEED = 7;

    public Paddle() {
        String path = getClass().getClassLoader()
                .getResource("breakoutresources/paddle.png").toString();
        setImage(new Image(path));
    }

    @Override
    public void act(long now) {
        if (getWorld().isKeyPressed(KeyCode.LEFT)) {
            setX(getX() - SPEED);
        }

        if (getWorld().isKeyPressed(KeyCode.RIGHT)) {
            setX(getX() + SPEED);
        }

        if (getX() < 0) {
            setX(0);
        }

        if (getX() + getWidth() > getWorld().getWidth()) {
            setX(getWorld().getWidth() - getWidth());
        }
    }
}