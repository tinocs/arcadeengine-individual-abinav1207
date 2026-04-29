package breakout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/*
 * Author: Abhinav Patti
 * Date: 27
 * Description: Is this lab correct and working? (yes)
 */

public class Breakout extends Application {
    private static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage){
        mainStage = stage;
        mainStage.setTitle("BREAKOUT");
        showMenu();
    }

    public static void showMenu(){
        VBox menu = new VBox(30);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: #2c3e50;");
        Text title = new Text("BREAKOUT");
        title.setStyle("-fx-font-size: 50px; -fx-fill: white; -fx-font-weight: bold;");
        Button playBtn = new Button("PLAY");
        playBtn.setPrefSize(150, 50);
        playBtn.setOnAction(e-> startGame());

        menu.getChildren().addAll(title, playBtn);
        mainStage.setScene(new Scene(menu, 800, 600));
        mainStage.show();
    }

    public static void startGame(){
        BallWorld world = new BallWorld();
        mainStage.setScene(new Scene(world, 800, 600));
        world.start();
    }
}