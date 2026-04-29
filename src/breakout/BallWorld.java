package breakout;

import java.io.*;
import java.util.*;

import engine.World;
/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class BallWorld extends World {
    private int level = 1;
    private Score score;
    private Lives lives;

    public BallWorld() {
        setPrefSize(800, 600);
    }
    @Override
    public void onDimensionsInitialized() {
        Ball ball = new Ball();
        Paddle paddle = new Paddle();

        ball.setX(getWidth() / 2 - ball.getWidth() / 2);
        ball.setY(getHeight() / 2 - ball.getHeight() / 2);

        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - 80);

        add(ball);
        
        add(paddle);

        score = new Score();
        score.setX(20);
        score.setY(30);
        getChildren().add(score);

        lives = new Lives();
        lives.setX(650);
        lives.setY(30);
        getChildren().add(lives);

        loadBricks("level" + level + ".txt");



        setOnMouseMoved(event -> {
            paddle.setX(event.getX() - paddle.getWidth() / 2);

            if (paddle.getX() < 0) {
                paddle.setX(0);
            }
            if (paddle.getX() + paddle.getWidth() > getWidth()) {
                paddle.setX(getWidth() - paddle.getWidth());
            }
        });
    }

    private void loadBricks(String filename){
        try{
            Scanner sc = new Scanner(new File("src/breakoutresources/" + filename));
             int row = sc.nextInt();
             int col = sc.nextInt();
             sc.nextLine();

             for (int r = 0; r < row; r++) {
                String line = sc.nextLine();
                for (int c = 0; c < col; c++) {
                    if(line.charAt(c) != '0'){
                    Brick brick = new Brick(line.charAt(c));
                    brick.setX(80 + c * 80);
                    brick.setY(60 + r * 35);
                    add(brick);
                    }
                }
            }
            sc.close();
        } catch (Exception e){
            System.out.println("Could not find File");
        }
        
    }

    @Override
    public void act(long now) {
        if(getObjects(Brick.class).isEmpty()){
            level++;
        
        if(level>2){
            Breakout.showMenu();
        }else{
            loadBricks("level" + level + ".txt");
            if(!getObjects(Brick.class).isEmpty()){
            Ball ball = getObjects(Ball.class).get(0);
            ball.setX(getWidth()/2);
            ball.setY(getHeight()/2);
            }
        }
    }
}
    public Score getScore() {
        return score;
    }
    public Lives getLives(){
        return lives;
    }
}