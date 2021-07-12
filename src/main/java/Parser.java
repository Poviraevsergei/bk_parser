
import Filter.FirstFilter;
import Model.Game;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

import static Input.DataInput.getGamesList;

public class Parser {
    public static void main(String[] args) throws IOException {
        int count =0;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siarhey.Pavirayeu\\IdeaProjects\\bk_parser\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        List<Game> list = getGamesList(driver);
/*        for (Game game : getGamesList(driver)){
            System.out.println(game);
        }*/

        System.out.println("Filter 1: Input:" + list.size());
        list = new FirstFilter().firstFilter(getGamesList(driver), driver);
         for (Game game : list) {
            if(game.isFirstFilterComplited()){
                System.out.println(game);
                if (game.isFirstFilterComplited()){
                    count++;
                }
            }
        }
        System.out.println("Filter 1: Output:" + count);
    }
}
