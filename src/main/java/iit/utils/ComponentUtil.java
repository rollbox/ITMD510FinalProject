package iit.utils;

import javafx.scene.control.Button;

public class ComponentUtil {

    public static Button getButton(String text, String theme) {
        Button button = new Button(text);
        button.getStyleClass().add(theme);
        return button;
    }
}
