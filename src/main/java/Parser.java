
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siarhey.Pavirayeu\\IdeaProjects\\bk_parser\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        List<Game> list = DataInput.inputData(driver);

        //First filter
        System.out.println("Filter 1: Input:" + list.size());
        list = new FirstFilter().firstFilter(list, driver);
        for (Game game : list) {
            if (game.isFirstFilterComplited()) {
                System.out.println(game);
            }
        }
        System.out.println("Filter 1: Output:" + list.size());

        //SecondFilter
        System.out.println("Filter 2: Input:" + list.size());
        list = new SecondFilter().secondFilter(list, driver);
        for (Game game : list) {
            if (game.isSecondFilterComplited()) {
                System.out.println(game);
            }
        }
        System.out.println("Filter 2: Output:" + list.size());

        //Third filter
        System.out.println("Filter 3: Input:" + list.size());
        list = ThirdFilter.thirdFilter(list);
        for (Game game : list) {
            if (game.isThirdFilterComplited()) {
                System.out.println(game);
            }
        }
        System.out.println("Filter 3: Output:" + list.size());
    }
}