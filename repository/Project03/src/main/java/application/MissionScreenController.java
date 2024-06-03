package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MissionScreenController {

    @FXML
    private Label label;

    @FXML
    private void initialize() {
        label.setText("임무 화면");
    }
}
