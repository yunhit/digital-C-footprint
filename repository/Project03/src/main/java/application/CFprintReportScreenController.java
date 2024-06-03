package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CFprintReportScreenController {

    @FXML
    private Label label;

    @FXML
    private void initialize() {
        label.setText("탄소 발자국 리포트 화면");
    }
}
