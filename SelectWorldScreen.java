package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
    

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
