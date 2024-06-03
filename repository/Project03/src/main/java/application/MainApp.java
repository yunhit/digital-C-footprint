package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStage;

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

            Scene scene = new Scene(root, 570, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/application.css").toExternalForm());

            primaryStage.setTitle("홈 화면");
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

            Scene scene = new Scene(root, 600, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);  // 창 크기 조절 비활성화
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMissionsScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/MissionScreen.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 600, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);  // 창 크기 조절 비활성화
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showReportScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/CFprintReportScreen.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 600, 800);
            scene.getStylesheets().add(MainApp.class.getResource("/css/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);  // 창 크기 조절 비활성화
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
