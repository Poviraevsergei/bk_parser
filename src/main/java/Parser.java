import Filter.FirstFilter;
import Filter.SecondFilter;
import Filter.ThirdFilter;
import Input.DataInput;
import Model.Game;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

public class Parser {
    public static void main(String[] args) throws IOException {
        int firstFilterCounter = 0;
        int secondFilterCounter = 0;
        int thirdFilterCounter = 0;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siarhey.Pavirayeu\\IdeaProjects\\bk_parser\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        List<Game> list = DataInput.inputData(driver);
        for (Game game : list) {
            System.out.println(game);
        }
        //First filter
        System.out.println("Filter 1 Input: " + list.size());
        for (Game game : list) {
            FirstFilter.firstFilter(game);
            if (game.isFirstFilterComplited()) {
                firstFilterCounter++;
            }
        }
        System.out.println("Filter 1 has " + firstFilterCounter + " success records.");

        //Second filter
        System.out.println("Filter 2 Input: " + list.size());
        for (Game game : list) {
            SecondFilter.secondFilter(game);
            if (game.isSecondFilterComplited()) {
                secondFilterCounter++;
            }
        }
        System.out.println("Filter 2 has " + secondFilterCounter + " success records.");

        //Third filter
        System.out.println("Filter 3 Input: " + list.size());
        for (Game game : list) {
            ThirdFilter.thirdFilter(game);
            if (game.isThirdFilterComplited()) {
                thirdFilterCounter++;
            }
        }
        System.out.println("Filter 3 has " + thirdFilterCounter + " success records.");
        driver.close();
    }
}