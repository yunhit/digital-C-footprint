package application;


import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CustomListCell extends ListCell<MissionState> {

    private HBox content;
    private Text itemText;
    private Button actionButton;
    public Seed seed;
    private MissionScreenController controller;
    private WebDriver driver;

    public CustomListCell(MissionScreenController controller, Seed seed) {
        super();
        this.seed = seed;
        this.controller = controller;

        itemText = new Text();
        itemText.getStyleClass().add("item-text");

        actionButton = new Button();
        actionButton.getStyleClass().add("custom-button");

        actionButton.setOnAction(event -> handleActionButtonClick());

        content = new HBox(itemText, actionButton);
        content.getStyleClass().add("custom-hbox");
        content.setAlignment(Pos.CENTER);

        setGraphic(content);
    }

    private void handleActionButtonClick() {
        MissionState state = getItem();
        if (state != null) {
            switch (state.getMissionText()) {
	            case "다크모드 사용하기":
	                // 다크모드 사용하기에 대한 동작 설정
	            	state.setClickCount(state.getClickCount() + 1);
	                switch (state.getClickCount()) {
	                    case 1:
	                    	windowDark();
	                        actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
	                        actionButton.getStyleClass().add("complete");
	                        break;
	                    case 2:
	                        actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
	                        actionButton.getStyleClass().add("recieve");
	                        actionButton.setDisable(true);
	                        controller.completeMission(state.getMissionText());
	                        reward();
	                        break;
	                }
	                break;
                case "절전모드 사용하기":
                	// 절전모드 사용하기에 대한 동작 설정
                    state.setClickCount(state.getClickCount() + 1);
                    switch (state.getClickCount()) {
                        case 1:
                        	windowSaver();
                            actionButton.getStyleClass().removeAll("complete", "receive", "after-login");
                            actionButton.getStyleClass().add("complete");
                            break;
                        case 2:
                        	actionButton.getStyleClass().removeAll("complete", "receive", "after-login");
                            actionButton.getStyleClass().add("receive");
                            actionButton.setDisable(true);
                            controller.completeMission(state.getMissionText());
                            reward();
                        	
                            break;
                    }
                    break;
                case "화면밝기 감소하기":
                    // 화면밝기 감소하기에 대한 동작 설정
                	state.setClickCount(state.getClickCount() + 1);
                    switch (state.getClickCount()) {
                        case 1:
                        	windowBrightness();
                            actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("complete");
                            break;
                        case 2:
                            actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("recieve");
                            actionButton.setDisable(true);
                            controller.completeMission(state.getMissionText());
                            reward();
                            break;
                    }
                    break;
                case "이메일함 정리하기":
                    // 이메일함 정리하기에 대한 동작 설정
                	state.setClickCount(state.getClickCount() + 1);
                    switch (state.getClickCount()) {
                        case 1:
                        	driver = mailCheck();
                            actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("after-login");
                            break;
                        case 2:
                        	mailLogin(driver);
                            actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("after-del");    
                            break;
                        case 3:
                        	actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("complete"); 
                            mailDel(driver);
                            break;
                        case 4:
                        	actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("recieve");  
                        	actionButton.setDisable(true);
                            controller.completeMission(state.getMissionText());
                            reward();
                        	
                    }
                    break;
                
                case "그린터치 사용하기":
                    // 그린터치 사용하기에 대한 동작 설정
                	state.setClickCount(state.getClickCount() + 1);
                    switch (state.getClickCount()) {
                        case 1:
                        	if(fileExists("C:\\GreenT\\GreenT.exe")) {
                        		runGreenT("C:\\GreenT\\GreenT.exe");
                        	}else {
                        		openGreenTWeb("https://www.kcen.kr/USR_main2016.jsp??=life/life35");
                        	}
                            actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("complete");
                            break;
                        case 2:
                            actionButton.getStyleClass().removeAll("complete", "recieve", "after-login", "after-del");
                            actionButton.getStyleClass().add("recieve");
                            actionButton.setDisable(true);
                            controller.completeMission(state.getMissionText());
                            reward();
                            break;
                    }
                    break;
            }
        }
    }
    
    public static WebDriver mailCheck() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--user-data-dir=C:\\Users\\samsung\\AppData\\Local\\Google\\Chrome");
    	
    	WebDriver driver = new ChromeDriver(options);
    	String url="https://mail.naver.com/v2/settings/folder";
    	driver.get(url);
    	
    	return driver;
    	
    }
    
    public static boolean mailLogin(WebDriver driver) {
    	WebElement el1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/table/tfoot/tr/td[2]/span[1]"));
    	String mailNum = el1.getText();
    	mailNum = mailNum.substring(0, mailNum.length() - 2);
    	int mailNumber1 = Integer.parseInt(mailNum);
    	
    	
    	return true;
    }
    
    public static boolean mailDel(WebDriver driver) {
    	WebElement el1 = driver.findElement(By.xpath("//*[@id=\\\"content\\\"]/div/div/table/tfoot/tr/td[2]/span[1]"));
    	String mailNum = el1.getText();
    	mailNum = mailNum.substring(0, mailNum.length() - 2);
    	int mailNumber2 = Integer.parseInt(mailNum);
    	
    	
    	return true;
    }
    
    private boolean fileExists(String path) {
    	File file = new File(path);
    	return file.exists();
    }
    
    private void openGreenTWeb(String url) {
    	try {
    		if(Desktop.isDesktopSupported()) {
    			Desktop desktop = Desktop.getDesktop();
    			if(desktop.isSupported(Desktop.Action.BROWSE)) {
    				desktop.browse(new URI(url));
    			}else {
    				System.err.println("Browse action is not supported on this platform.");
    			}
    		}else {
    			System.err.println("Desktop is not supported on this platform.");
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void runGreenT(String command) {
    	try {
    		Runtime.getRuntime().exec(command);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    private void reward() {
        seed.addSeed(1);
        Storage.saveSeedCount(seed.getCount());
    }
    
    
    public static boolean windowSaver() {
    	try {
            Robot robot = new Robot();
            String command = "cmd /c start ms-settings:powersleep";
            Runtime.getRuntime().exec(command);
            robot.delay(4000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1500);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_F4);
         } catch (Exception e) {
            e.printStackTrace();
         }
        return true;
     }
    
    public static boolean windowBrightness() {
        try {
           Robot robot = new Robot();
           String command = "cmd /c start ms-settings:display";
           Runtime.getRuntime().exec(command);
           robot.delay(3000);
           robot.keyPress(KeyEvent.VK_TAB);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_TAB);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_TAB);
           robot.keyRelease(KeyEvent.VK_TAB);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_ENTER);
           robot.keyRelease(KeyEvent.VK_ENTER);
           robot.delay(150);
           for (int i = 0; i < 30; i++) {
              robot.keyPress(KeyEvent.VK_DOWN);
              robot.keyRelease(KeyEvent.VK_DOWN);
              robot.delay(50);
           }
           robot.delay(100);
           robot.keyPress(KeyEvent.VK_ALT);
           robot.keyPress(KeyEvent.VK_F4);
           robot.keyRelease(KeyEvent.VK_ALT);
           robot.keyRelease(KeyEvent.VK_F4);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return true;
     }
     
     public static boolean windowDark() {
        try {
           Robot robot = new Robot();
           String command = "cmd /c start ms-settings:colors";
           Runtime.getRuntime().exec(command);
           robot.delay(3000);
           robot.keyPress(KeyEvent.VK_TAB);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_TAB);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_TAB);
           robot.keyRelease(KeyEvent.VK_TAB);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_ENTER);
           robot.keyRelease(KeyEvent.VK_ENTER);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_DOWN);
           robot.keyRelease(KeyEvent.VK_DOWN);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_ENTER);
           robot.keyRelease(KeyEvent.VK_ENTER);
           robot.delay(150);
           robot.keyPress(KeyEvent.VK_ALT);
           robot.keyPress(KeyEvent.VK_F4);
           robot.keyRelease(KeyEvent.VK_ALT);
           robot.keyRelease(KeyEvent.VK_F4);
        } catch (Exception e) {          
           e.printStackTrace();
        }
        return true;
     }

    @Override
    protected void updateItem(MissionState state, boolean empty) {
        super.updateItem(state, empty);

        if (empty || state == null) {
            setText(null);
            setGraphic(null);
        } else {
            itemText.setText(state.getMissionText());
            actionButton.setDisable(state.isButtonDisabled());
            actionButton.getStyleClass().clear();
            actionButton.getStyleClass().add("custom-button");
            actionButton.getStyleClass().add(state.getButtonStyleClass());
            setGraphic(content);
        }
    }
}
