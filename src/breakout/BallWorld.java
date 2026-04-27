package breakout;

import engine.World;
/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class BallWorld extends World {

    private Score score;
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

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 8; col++) {
                Brick brick = new Brick();
                brick.setX(80 + col * 80);
                brick.setY(60 + row * 35);
                add(brick);
            }
        }

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

    @Override
    public void act(long now) {
    }
    public Score getScore() {
        return score;
    }
}