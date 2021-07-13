package Input;

import Model.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class DataInput {
    public static List<Game> inputData(WebDriver driver) throws IOException {
        String firstUrl = "https://www.flashscore.ru/match/";
        String secondUrlTable = "/#standings/table/overall";
        String secondUrlСoefficient = "/#odds-comparison/1x2-odds/full-time";

        //Load Teams and Time
        List<Game> resultList = TeamsAndTime.getTeamsAndGameTimeList(driver);

        //Load table stats
        for (Game game : resultList) {
            try {
                driver.get(firstUrl + game.getId() + secondUrlTable);
                WebElement element = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                String HTML = element.getAttribute("innerHTML");
                TableStats.getTableStats(game, HTML);
                ScoreAndMissedHomeAndAway.getTableStats(game, HTML);
            } catch (Exception e) {
            }
        }

        //Load Coefficients
        for (Game game : resultList) {
            try {
                driver.get(firstUrl + game.getId() + secondUrlСoefficient);
                WebElement element2 = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                String HTML2 = element2.getAttribute("innerHTML");
                CoefficientData.setCoefficient(HTML2, game);
            } catch (Exception e) {
            }
        }
        return resultList;
    }
}