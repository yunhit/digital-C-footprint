package prac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class Prac1 {
	
	public static void main(String[] args) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to proceed?");
        alert.setContentText("Click OK to proceed or Cancel to exit.");

        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                System.out.println("User clicked OK, proceeding to next step...");
                // 여기에 다음 단계로 넘어가는 코드를 추가할 수 있습니다.
            } else {
                System.out.println("User clicked Cancel or closed the dialog, exiting...");
                // 여기에 종료하는 코드를 추가할 수 있습니다.
                primaryStage.close();
            }
        });
	}
	
	public boolean window() {
		try {
            Robot robot = new Robot();
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress('I');
            robot.keyRelease('I');
            robot.keyRelease(KeyEvent.VK_WINDOWS);
        } catch (AWTException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	public boolean mailDel() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-data-dir=C:\\Users\\ha653\\AppData\\Local\\Google\\Chrome");
		
		WebDriver driver = new ChromeDriver(options);
		String url = "https://mail.naver.com/v2/settings/folder";
		driver.get(url);
		try {
            Thread.sleep(2000); // 2000 milliseconds = 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		WebElement el1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/table/tfoot/tr/td[2]/span[1]"));
		String mailNum=el1.getText();
		mailNum=mailNum.substring(0,mailNum.length()-2);
		int mailNumber1=Integer.parseInt(mailNum);
		System.out.println(mailNumber1);
		return true;
	}

}
