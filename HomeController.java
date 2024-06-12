package application;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class HomeController {

    @FXML
    private ImageView mainImageView;

    @FXML
    private Label seedLabel;

    @FXML
    private Button worldButton;

    @FXML
    private ImageView worldIcon;

    @FXML
    private Button missionsButton;

    @FXML
    private ImageView missionsIcon;

    @FXML
    private Button calculatorButton;

    @FXML
    private ImageView calculatorIcon;

    @FXML
    private Button treeButton1;

    @FXML
    private Button treeButton2;

    @FXML
    private Button treeButton3;

    @FXML
    private Button treeButton4;

    @FXML
    private Button treeButton5;

    @FXML
    private Button treeButton6;

    @FXML
    private Button treeButton7;

    @FXML
    private Button treeButton8;

    @FXML
    private Button treeButton9;
    


    public Seed seed;
    public Tree tree;
    private Image treeImage;

    @FXML
    private void initialize() {
        // 선택된 월드에 따라 이미지 경로 설정
    	updateSelectedWorld();

        worldIcon.setImage(loadImage("/images/world_icon.png"));
        missionsIcon.setImage(loadImage("/images/missions_icon.png"));
        calculatorIcon.setImage(loadImage("/images/calculator_icon.png"));

        // 버튼 이벤트 설정
        worldButton.setOnAction(e -> MainApp.showWorldScreen());
        missionsButton.setOnAction(e -> MainApp.showMissionsScreen());
        calculatorButton.setOnAction(e -> MainApp.showCalculatorScreen());

        // 초기 씨앗 수 설정
        seed = new Seed(Storage.loadSeedCount());
        updateSeedLabel();
        
        tree = new Tree(Storage.loadTreeCount());
        
        
    }
    public void updateSelectedWorld() {
        String selectedWorld = SelectedWorld.getSelectedWorld();
        if (selectedWorld != null) {
        	int worldNumber = extractWorldNumber(selectedWorld);
                if (worldNumber == 1) {
                mainImageView.setImage(loadImage("/images/HomeScreen.png"));
                treeImage = loadImage("/images/Tree_icon.png");
            } else {
                String worldScreenPath = "/images/World_Screen_" + worldNumber + ".png";
                String treePath = "/images/world_" + worldNumber + ".png";


                mainImageView.setImage(loadImage(worldScreenPath));
                treeImage = loadImage(treePath);
            }
        } else {
            mainImageView.setImage(loadImage("/images/HomeScreen.png"));
            treeImage = loadImage("/images/Tree_icon.png");
        }
         mainImageView.setFitWidth(700);  // 이미지의 넓이를 조정
         mainImageView.setFitHeight(700);  // 이미지의 높이를 조정
         mainImageView.setPreserveRatio(true);  // 비율을 유지하여 확대
     }
    private int extractWorldNumber(String selectedWorld) {
        try {
            return Integer.parseInt(selectedWorld.replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            System.err.println("Invalid world number: " + selectedWorld);
            return 1; // 기본 값으로 1을 반환
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

    @FXML
    private void plantTree(javafx.event.ActionEvent event) {
        if (seed.getCount() <= 0) {
            // 씨앗이 없을 때 처리
            return;
        }

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getGraphic() != null) {
            // 이미 나무가 심어져 있으면 클릭 불가
            return;
        }

        ImageView treeView = new ImageView(treeImage);
        treeView.setFitWidth(50);  // 크기 줄이기
        treeView.setFitHeight(50);  // 크기 줄이기
        treeView.setRotate(-45);  // 왼쪽으로 45도 회전

        clickedButton.setGraphic(treeView);

        // 나무 심는 애니메이션
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), treeView);
        scaleTransition.setFromX(0.1);
        scaleTransition.setFromY(0.1);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);
        scaleTransition.play();

        seed.useSeed(1);
        Storage.saveSeedCount(seed.getCount());
        updateSeedLabel();
        
        tree.addTree(1);
        Storage.saveTreeCount(tree.getCount());

    }

    private void updateSeedLabel() {
        seedLabel.setText(String.valueOf(seed.getCount()));
    }
}
