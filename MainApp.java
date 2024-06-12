package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStage;
    private static MainApp instance;

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        showHomeScreen();
    }

    public static void showHomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/MainApp.fxml"));
            Parent root = loader.load();

            
            HomeController controller = loader.getController();
            controller.updateSelectedWorld(); // 필요한 경우 주석 해제

            Scene scene = new Scene(root, 570, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

            primaryStage.setTitle("Green Planet");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);  // 창 크기 조절 비활성화
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showWorldScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SelectWorldScreen.fxml"));
            Parent root = loader.load();

            SelectWorldScreenController controller = loader.getController();
            controller.setMainApp(getInstance());

            Scene scene = new Scene(root, 570, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

            primaryStage.setTitle("Green Planet");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);  // 창 크기 조절 비활성화
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMissionsScreen() {
    	 try {
         	
         	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MissionScreen.fxml"));
         	Parent root = loader.load();
         	MissionScreenController controller = loader.getController();

         	Seed seed = new Seed(0);
         	controller.setSeed(seed);
         
         	
            Scene scene = new Scene(root, 500, 650);
            scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

            primaryStage.setTitle("Green Planet");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    public static void showCalculatorScreen() {
        try {
            
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/CarbonFootScreen.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());
            
            primaryStage.setTitle("Green Planet");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);  // 창 크기 조절 비활성화
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MainApp() {
        instance = this;
    }

    public static MainApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

