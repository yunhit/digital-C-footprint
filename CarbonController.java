package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarbonController implements Initializable {
    @FXML 
    private ImageView imageView;
    @FXML 
    private TextField inputField1;
    @FXML 
    private Label resultLabel1;
    @FXML 
    private Label treeLabel1;
    @FXML 
    private TextField inputField2;
    @FXML 
    private Label resultLabel2;
    @FXML 
    private Label treeLabel2;
    @FXML 
    private TextField inputField3;
    @FXML 
    private Label resultLabel3;
    @FXML 
    private Label treeLabel3;
    @FXML 
    private TextField inputField4;
    @FXML 
    private Label resultLabel4;
    @FXML 
    private Label treeLabel4;
    @FXML 
    private Button backButton;
    private MainApp mainApp;

    private static final double TREE_CO2_ABSORPTION_PER_YEAR = 36.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 이미지뷰 설정
        try {
            imageView.setImage(loadImage("/images/calculatorScreen.png"));
            // 이미지를 표시할 너비와 높이를 설정
            imageView.setFitWidth(500);
            imageView.setFitHeight(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 뒤로 가기 버튼 이벤트핸들러
        backButton.setOnAction(event -> mainApp.showHomeScreen());

        // 텍스트 입력 리스너
        addInputListener(inputField1, resultLabel1, treeLabel1, "entertainment");
        addInputListener(inputField2, resultLabel2, treeLabel2, "social");
        addInputListener(inputField3, resultLabel3, treeLabel3, "productivity");
        addInputListener(inputField4, resultLabel4, treeLabel4, "game");
    }

    private void addInputListener(TextField inputField, Label resultLabel, Label treeLabel, String type) {
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double minutes = Double.parseDouble(newValue);
                double carbonKg = calculateCarbonFootprint(minutes, type);
                resultLabel.setText(String.format("CO2:       %.2f", carbonKg));
                double treeCount = carbonKg / TREE_CO2_ABSORPTION_PER_YEAR;
            } catch (NumberFormatException e) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setTitle("Confirmation Dialog");
            	alert.setHeaderText("유효한 숫자가 아닙니다.");
            	alert.setContentText("입력하신 숫자를 다시 한번 확인하세요!");
            	alert.showAndWait();
            	
            }
        });
    }

    private double calculateCarbonFootprint(double minutes, String type) {
        switch (type) {
            case "entertainment":
                return (minutes / 30) * 1.6;
            case "social":
                double messagesPerMinute = 125 / 60.0;
                double carbonPerMessage = 0.014;
                return messagesPerMinute * minutes * carbonPerMessage / 1000.0;
            case "productivity":
                return minutes * 0.0036; 
            case "game":
                return (minutes / 2400) * 1.0;
            default:
                return 0.0;
        }
    }
    
    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (NullPointerException e) {
            System.err.println("이미지 로드 실패: " + path);
            return null;
        }
    }
}
