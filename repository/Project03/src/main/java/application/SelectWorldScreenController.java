package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SelectWorldScreenController {

    @FXML
    private Label label;

    @FXML
    private void initialize() {
        label.setText("월드 선택 화면");
    }
}
