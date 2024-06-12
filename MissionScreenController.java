package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MissionScreenController {

    @FXML
    private ListView<MissionState> ongoingMissionListView;

    @FXML
    private ListView<MissionState> completeMissionListView;
    
    @FXML
    private Button HomeButton;
    
    @FXML
    private ImageView HomeIcon;
    
    private ObservableList<MissionState> ongoingMissions;
    private ObservableList<MissionState> completeMissions;
    
    private Seed seed;
    
    public void setSeed(Seed seed) {
    	this.seed = seed;
    	initialize();
    }

    public void initialize() {
        ongoingMissions = FXCollections.observableArrayList(
            new MissionState("다크모드 사용하기", false, "custom-button", 0),
            new MissionState("화면밝기 감소하기", false, "custom-button", 0),
            new MissionState("절전모드 사용하기", false, "custom-button", 0),
            new MissionState("그린터치 사용하기", false, "custom-button", 0),
            new MissionState("이메일함 정리하기", false, "custom-button", 0)
            
        );
        completeMissions = FXCollections.observableArrayList();
       
        ongoingMissionListView.setItems(ongoingMissions);
        ongoingMissionListView.setCellFactory(param -> new CustomListCell(this, seed));

        completeMissionListView.setItems(completeMissions);
        completeMissionListView.setCellFactory(param -> new CustomListCell(this, seed));
        
        // 홈버튼
        HomeButton.setOnAction(e -> 
        	MainApp.showHomeScreen());
        
    }
    
    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (NullPointerException e) {
            System.err.println("이미지 로드 실패: " + path);
            return null;
        }
    }
    
    public void completeMission(String mission) {
        MissionState completedMission = ongoingMissions.stream()
                .filter(state -> state.getMissionText().equals(mission))
                .findFirst()
                .orElse(null);
        
        if (completedMission != null) {
            completedMission.setButtonDisabled(true);
            completedMission.setButtonStyleClass("recieve");
            completeMissions.add(completedMission);
            ongoingMissions.remove(completedMission);
        }
    }
    
}
