package WorldSelection;

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
    
    private SelectWorldScreen MainApp;

    private String selectedWorld;
    
    private boolean[] worldUnlocked;

    @FXML
    private void initialize() {
    	// 잠금 상태 초기화
        worldUnlocked = new boolean[10];
        // 모든 월드가 잠긴 상태로 가정하고 시작
        for (int i = 0; i < worldUnlocked.length; i++) {
            worldUnlocked[i] = false;
        }

        // 첫 번째 월드만 잠금 해제
        worldUnlocked[0] = true;
        // 화면에 다양한 월드 이미지 추가
    	    addWorldImage("world_1.png", "Level 1", "Winter", 0);
    	    addWorldImage("world_2.png", "Level 2", "Green Tree", 1);
    	    addWorldImage("world_3.png", "Level 3", "CherryBlossom", 2);
    	    addWorldImage("world_4.png", "Level 4", "Lilac Tree", 3);
    	    addWorldImage("world_5.png", "Level 5", "Apple Tree", 4);
    	    addWorldImage("world_6.png", "Level 6", "Coral Leef",5);
    	    addWorldImage("world_7.png", "Level 7", "Palm", 6);
    	    addWorldImage("world_8.png", "Level 8", "Maple Tree", 7);
    	    addWorldImage("world_9.png", "Level 9", "Pine Tree", 8);
    	    addWorldImage("world_10.png", "Level 10", "Chirstmas Tree", 9);
        //스크롤 막대 적절한 크기로 설정
        worldScrollPane.setPrefSize(600, 900);
    }
    
    
    public void setMainApp(SelectWorldScreen MainApp) {
        this.MainApp = MainApp;
    }

    private void addWorldImage(String imageName,  String levelText, String seasonText, int row) {
    	
     // 이미지 생성
        Image worldImage = new Image(getClass().getResourceAsStream("/WorldSelection/images/" + imageName));
        ImageView worldImageView = new ImageView(worldImage);
        
     //이미지가 표시될 너비와 높이 지정
        worldImageView.setFitWidth(280);
        worldImageView.setFitHeight(305);
        
     // 자물쇠 이미지 생성
        Image lockImage = new Image(getClass().getResourceAsStream("/WorldSelection/images/lock.png"));
        ImageView lockImageView = new ImageView(lockImage);
        lockImageView.setFitWidth(150);
        lockImageView.setFitHeight(150);
     // StackPane에 월드 이미지와 자물쇠 이미지 추가
        StackPane stackPane = new StackPane(worldImageView, lockImageView);
        stackPane.setAlignment(Pos.CENTER); // 이미지를 가운데로 정렬
        stackPane.setMargin(lockImageView, new Insets(10)); // 자물쇠 위치 조정

        
 // 잠긴 상태라면
        if (!worldUnlocked[row]) {
            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1); // 회색조로 변경
            worldImageView.setEffect(grayscale);
        
            stackPane.setOnMouseClicked(event -> {
            	selectedWorld = imageName;
            	System.out.println("Selected World Image: " + imageName);
            	SelectedWorld.setSelectedWorld(imageName); // 선택된 월드를 저장
            	MainApp.showWorldScreen(); // 메인 화면으로 돌아가기
            });
 
            Text level = new Text(levelText);
            level.setFont(Font.loadFont(getClass().getResourceAsStream("PoetsenOne-Regular.ttf"), 30));
            Text season = new Text(seasonText);
            season.setFont(Font.loadFont(getClass().getResourceAsStream("PoetsenOne-Regular.ttf"), 20));
        
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
            	hBox.getChildren().setAll(stackPane,textBox);
            } else {
            	hBox.getChildren().setAll(textBox, stackPane);
            }

            worldGridPane.setPadding(new Insets(160, 0, 0, 15));  // 배너에 가리지 않게 상단에 100 픽셀 여백 추가
            worldGridPane.add(hBox, 0, row);
            worldGridPane.setStyle("-fx-background-color: #C7F2FF;"); // 배경색을 흰색으로 설정
            
// 잠긴 상태가 아니라면
        } else {   
  
        	worldImageView.setOnMouseEntered(event -> {
        		worldImageView.setOpacity(0.7); // 불투명도를 낮춤
        	});

        	worldImageView.setOnMouseExited(event -> {
        		worldImageView.setOpacity(1.0); // 원래 상태로 복원
        	});

        	worldImageView.setOnMouseClicked(event -> {
        		selectedWorld = imageName;
            	System.out.println("Selected World Image: " + imageName);
            	SelectedWorld.setSelectedWorld(imageName); // 선택된 월드를 저장
            	MainApp.showWorldScreen(); // 메인 화면으로 돌아가기
        	});

        	Text level = new Text(levelText);
        	level.setFont(Font.loadFont(getClass().getResourceAsStream("PoetsenOne-Regular.ttf"), 30));
        	Text season = new Text(seasonText);
        	season.setFont(Font.loadFont(getClass().getResourceAsStream("PoetsenOne-Regular.ttf"), 20));

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
        		hBox.getChildren().addAll(worldImageView, textBox);
        	} else {
        		hBox.getChildren().addAll(textBox, worldImageView);
        	}

        	worldGridPane.setPadding(new Insets(160, 0, 0, 15)); // 배너에 가리지 않게 상단에 100 픽셀 여백 추가
        	worldGridPane.add(hBox, 0, row);
        	worldGridPane.setStyle("-fx-background-color: #C7F2FF;"); //
    	
        }
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
            System.out.println("Selected World Image: " + selectedWorld);
            SelectedWorld.setSelectedWorld(selectedWorld);
            MainApp.showWorldScreen();
        });
    }

}



