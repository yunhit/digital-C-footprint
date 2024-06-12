package application;

public class MissionState {
    private String missionText;
    private boolean isButtonDisabled;
    private String buttonStyleClass;
    private int clickCount;

    public MissionState(String missionText, boolean isButtonDisabled, String buttonStyleClass, int clickCount) {
        this.missionText = missionText;
        this.isButtonDisabled = isButtonDisabled;
        this.buttonStyleClass = buttonStyleClass;
        this.clickCount = clickCount;
    }

    public String getMissionText() {
        return missionText;
    }

    public void setMissionText(String missionText) {
        this.missionText = missionText;
    }

    public boolean isButtonDisabled() {
        return isButtonDisabled;
    }

    public void setButtonDisabled(boolean buttonDisabled) {
        isButtonDisabled = buttonDisabled;
    }

    public String getButtonStyleClass() {
        return buttonStyleClass;
    }

    public void setButtonStyleClass(String buttonStyleClass) {
        this.buttonStyleClass = buttonStyleClass;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}
