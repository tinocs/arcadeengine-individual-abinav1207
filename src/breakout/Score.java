package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 * Author: Abhinav Patti
 * Date: 26
 * Description: Is this lab correct and working? (yes/no)
 */

public class Score extends Text {

    private int value;

    public Score() {
        value = 0;
        setFont(new Font(24));
        updateDisplay();
    }

    public void updateDisplay() {
        setText("Score: " + value);
    }
    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
        updateDisplay();
    }
}