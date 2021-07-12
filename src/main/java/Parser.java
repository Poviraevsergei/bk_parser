
import Model.Game;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;

import static Input.DataInput.getGamesList;

public class Parser {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siarhey.Pavirayeu\\IdeaProjects\\bk_parser\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        for (Game game : getGamesList(driver)) {
            System.out.println(game);
        }
    }
}