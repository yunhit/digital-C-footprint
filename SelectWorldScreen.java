package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SelectWorldScreen extends Application {
    
    private Stage primaryStage;
    private String selectedWorld;
        
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
        	
     //GUI를 만들기 위해 FXML 파일을 Java 객체로 변환
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/SelectWorldScreen.fxml"));
            AnchorPane mainLayoutAnchorPane = (AnchorPane) loader.load();
                        
            SelectWorldScreenController controller = loader.getController();
           
            
            
     //상단 배너에 들어갈 텍스트 추가
            Text text = new Text("W o r l d");
            text.setFill(Color.BLACK); // 글자 색 변경
            String fontPath = getClass().getResource("PoetsenOne-Regular.ttf").toExternalForm();
            text.setFont(Font.loadFont(fontPath, 50)); 
                      
            AnchorPane textContainer = new AnchorPane();  // text를 담을 수 있는 AnchorPane 생성
            AnchorPane.setTopAnchor(text, 20.0); // 위쪽으로 20 픽셀 여백
            AnchorPane.setLeftAnchor(text, 200.0); // 왼쪽으로 20 픽셀 여백
  
            textContainer.getChildren().add(text);  // AnchorPane에 텍스트 추가
             
    
     // 배너 추가
            Rectangle banner = new Rectangle(550, 100); //사이즈
            banner.setFill(Color.BEIGE); //색상
            banner.setOpacity(1); //투명도
            banner.setStyle("-fx-arc-width: 10; -fx-arc-height: 10;"); // 모서리를 둥글게 
            // 그림자 설정
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5); // 그림자의 반경 설정
            dropShadow.setOffsetX(3); // 그림자의 가로 위치 설정
            dropShadow.setOffsetY(3); // 그림자의 세로 위치 설정
            dropShadow.setColor(Color.GRAY); // 그림자의 색상 설정
           
            banner.setEffect(dropShadow); // 배너에 그림자 적용
            AnchorPane.setRightAnchor(banner, 18.0); // 위쪽으로 20 픽셀 여백
            AnchorPane.setLeftAnchor(banner, 15.0); // 왼쪽으로 20 픽셀 여백
  
            
    // 배너와 텍스트를 함께 래핑
            AnchorPane bannerBox = new AnchorPane();
            bannerBox.getChildren().addAll(banner, text); // 배너와 텍스트를 함께 추가
            
            
    // 뒤로가기 버튼
            Button backButton = new Button("🔙");
            backButton.setPrefSize(70, 60);
            backButton.setStyle("-fx-background-color: #FFA778; -fx-text-fill: black; -fx-font-size: 30px ");
        
          
            backButton.setOnAction(event -> MainApp.showWorldScreen());
          
            
            // AnchorPane에 버튼을 배치하고 위치 설정
            AnchorPane.setTopAnchor(backButton, 20.0);
            AnchorPane.setLeftAnchor(backButton, 30.0);
                  
            
   //지금까지 설정한것 모두 root 에 추가
            AnchorPane root = new AnchorPane();
            root.getChildren().addAll(mainLayoutAnchorPane,  bannerBox, backButton);
         
   //scene 생성 및 설정
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            primaryStage.setTitle("월드 선택 화면");
       
            primaryStage.setWidth(600);
            primaryStage.setHeight(900);
            
            primaryStage.setMinWidth(400); // 최소 너비 설정
            primaryStage.setMinHeight(600); // 최소 높이 설정
            
            primaryStage.setMaxWidth(800); // 최대 너비 설정
            primaryStage.setMaxHeight(1200); // 최대 높이 설정
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
   
        } catch(Exception e) {e.printStackTrace();}
    }
    
 /*
    public void setWorld(String world) {
        this.selectedWorld = world;
        // 메인 게임 화면에 선택된 월드를 표시하는 메소드 호출
        System.out.println("Selected World: " + selectedWorld);
    
    */

    public static void main(String[] args) {
        launch(args);
    }
    
    
}