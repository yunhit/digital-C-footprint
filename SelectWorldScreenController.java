package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Button;


public class SelectWorldScreenController {

	@FXML
    private ScrollPane worldScrollPane;
    
    @FXML
    private GridPane worldGridPane;
    
    private MainApp mainApp;

    private String selectedWorld;
    
    private boolean[] worldUnlocked;

    @FXML
    private Button backButton;
    
    @FXML
    private void initialize() {
    	// 잠금 상태 초기화
        worldUnlocked = new boolean[10];
        int treeCount = Storage.loadTreeCount();

        // 첫 번째 월드만 잠금 해제
        worldUnlocked[0] = true;
        
        // 월드를 잠금 해제하는 조건을 설정
        for (int i = 1; i < worldUnlocked.length; i++) {
            worldUnlocked[i] = treeCount >= (i * 9);
           
        }
        
        // 화면에 다양한 월드 이미지 추가
        addWorldImage("/images/world_1.png", "Level 1", "Green Tree", 0);
        addWorldImage("/images/world_2.png", "Level 2", "Winter", 1);
        addWorldImage("/images/world_3.png", "Level 3", "CherryBlossom", 2);
        addWorldImage("/images/world_4.png", "Level 4", "Lilac Tree", 3);
        addWorldImage("/images/world_5.png", "Level 5", "Apple Tree", 4);
        addWorldImage("/images/world_6.png", "Level 6", "Coral Leef", 5);
        addWorldImage("/images/world_7.png", "Level 7", "Palm", 6);
        addWorldImage("/images/world_8.png", "Level 8", "Maple Tree", 7);
        addWorldImage("/images/world_9.png", "Level 9", "Pine Tree", 8);
        addWorldImage("/images/world_10.png", "Level 10", "Christmas Tree", 9);
        //스크롤 막대 적절한 크기로 설정
        worldScrollPane.setPrefSize(600, 900);
        
        // 뒤로 가기 버튼 이벤트핸들러
        backButton.setOnAction(event -> mainApp.showHomeScreen());
    }
    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    private void addWorldImage(String imageName,  String levelText, String seasonText, int row) {
    	
        // 이미지 생성
           Image worldImage = new Image(getClass().getResourceAsStream(imageName));
           ImageView worldImageView = new ImageView(worldImage);
           
        //이미지가 표시될 너비와 높이 지정
           worldImageView.setFitWidth(280);
           worldImageView.setFitHeight(305);
           
        // 자물쇠 이미지 생성
           Image lockImage = new Image(getClass().getResourceAsStream("/images/lock.png"));
           ImageView lockImageView = new ImageView(lockImage);
           lockImageView.setFitWidth(150);
           lockImageView.setFitHeight(150);
           
        // StackPane에 월드 이미지와 자물쇠 이미지 추가
           StackPane stackPane = new StackPane(worldImageView, lockImageView);
           stackPane.setAlignment(Pos.CENTER); // 이미지를 가운데로 정렬
           stackPane.setMargin(lockImageView, new Insets(10)); // 자물쇠 위치 조정
           
        // 잠긴 상태라면
           if(!worldUnlocked[row]) {
        	   ColorAdjust grayscale = new ColorAdjust();
        	   grayscale.setSaturation(-1);
        	   worldImageView.setEffect(grayscale);
        	   
        	   lockImageView.setVisible(true);
           }else {
        	   worldImageView.setOnMouseEntered(event -> {
        		   worldImageView.setOpacity(0.7);
        	   });
        	   
        	   worldImageView.setOnMouseExited(event ->{
        		   worldImageView.setOpacity(1.0);
        	   });
        	   
        	   worldImageView.setOnMouseClicked(event ->{
        		   selectedWorld = imageName;
        		   SelectedWorld.setSelectedWorld(imageName);
        	   });
        	   
        	   stackPane.getChildren().remove(lockImageView);
           }
           
           Text level = new Text(levelText);
           level.setFont(Font.loadFont(getClass().getResourceAsStream("/font/MalangmalangB.ttf"), 30));
           Text season = new Text(seasonText);
           season.setFont(Font.loadFont(getClass().getResourceAsStream("/font/MalangmalangB.ttf"), 20));

           HBox hBox = new HBox();
           hBox.setSpacing(10);
           hBox.setPadding(new Insets(0));
           hBox.setStyle("-fx-alignment: center;");

           VBox textBox = new VBox(level, season);
           textBox.setSpacing(5);
           textBox.setPadding(new Insets(60));
           textBox.setStyle("-fx-alignment: center-left;");
           
        // 짝수 줄과 홀수 줄을 번갈아 배치
           if (row % 2 == 0) {
               hBox.getChildren().setAll(stackPane, textBox);
           } else {
               hBox.getChildren().setAll(textBox, stackPane);
           }

           worldGridPane.setPadding(new Insets(160, 0, 0, 15));  // 배너에 가리지 않게 상단에 100 픽셀 여백 추가
           worldGridPane.add(hBox, 0, row);
           worldGridPane.setStyle("-fx-background-color: #C7F2FF;"); // 배경색을 흰색으로 설정
           
       }
    private void unlockWorld(int row, StackPane stackPane, ImageView worldImageView, ImageView lockImageView) {
        // 월드를 잠금 해제
        worldUnlocked[row] = true;
        SelectedWorld.setSelectedWorld("world_" + (row + 1) + ".png"); // 선택된 월드 저장

        // 회색 필터와 자물쇠 이미지 제거
        worldImageView.setEffect(null);
        stackPane.getChildren().remove(lockImageView);

        // UI 업데이트
        worldImageView.setOnMouseClicked(event -> {
            selectedWorld = "world_" + (row + 1) + ".png";
            SelectedWorld.setSelectedWorld(selectedWorld);
           
        });
    }

}