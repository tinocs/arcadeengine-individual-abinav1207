package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

/*
 * Author: Abhinav Patti
 * Date: 28
 * Description: Lives display class extending Text
 */

public class Lives extends Text {

    private int value;

    public Lives() {
        value = 3;
        setFont(new Font("Arial", 24));
        setFill(Color.WHITE);
        updateDisplay();
    }

    public void updateDisplay() {
        setText("Lives: " + value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateDisplay();
    }
}