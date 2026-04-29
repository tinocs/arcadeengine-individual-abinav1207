package breakout;

import engine.Actor;
import javafx.scene.image.Image;

/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class Ball extends Actor {

    private double dx;
    private double dy;


    public Ball() {
        String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
        setImage(new Image(path));

        dx = 5;
        dy = 5;
    }


    @Override
    public void act(long now) {
        move(dx, dy);

        if (getX() <= 0) {
            setX(0);
            dx = -dx;
        }

        if (getY() <= 0) {
            setY(0);
            dy = -dy;
        }

        if (getX() + getWidth() >= getWorld().getWidth()) {
            setX(getWorld().getWidth() - getWidth());
            dx = -dx;
        }

        if (getY() + getHeight() >= getWorld().getHeight()) {
            BallWorld world = (BallWorld) getWorld();
            setY(getWorld().getHeight() - getHeight());
            dy = -dy;
            world.getScore().setValue(world.getScore().getValue() - 1000);
        }

        Paddle paddle = getOneIntersectingObject(Paddle.class);
        if (paddle != null) {
            dy = -Math.abs(dy);
            setY(paddle.getY() - getHeight());
        }

        Brick brick = getOneIntersectingObject(Brick.class);
        if (brick != null) {
            if (getX() >= brick.getX() && getX() + getWidth() <= brick.getX() + brick.getWidth()) {
                dy = -dy;
            } else if (getY() >= brick.getY() && getY() + getHeight() <= brick.getY() + brick.getHeight()) {
                dx = -dx;
            } else {
                dx = -dx;
                dy = -dy;
            }

            if(brick.getType() =='1'){
                breakNeighbors(brick);
            }else{
                getWorld().remove(brick);
            }
            BallWorld world = (BallWorld) getWorld();
            world.getScore().setValue(world.getScore().getValue() + 100);
        }
    }

    private void breakNeighbors(Brick b){
        BallWorld world = (BallWorld) getWorld();
        double x = b.getX() + b.getWidth()/2;
        double y = b.getY()+ b.getHeight()/2;
        java.util.List<Brick> allBricks = world.getObjects(Brick.class);
        java.util.ArrayList<Brick> toRemove = new java.util.ArrayList<>();

        for (Brick  a : allBricks){
            if(a.getType()=='1'){
                double bX = a.getX() + a.getWidth()/2;
                double bY = a.getY() + a.getHeight()/2;
                double distance = Math.sqrt(Math.pow(x - bX, 2) + Math.pow(y - bY, 2));

                if(distance <100){
                    toRemove.add(a);
                }
            }
        }
        for(Brick a : toRemove){
            world.remove(a);
            world.getScore().setValue(world.getScore().getValue() + 100);
        }

    }
}