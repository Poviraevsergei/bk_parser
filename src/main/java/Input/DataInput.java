package Input;

import Model.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class DataInput {
    public static List<Game> inputData(WebDriver driver) throws IOException {
        int errorCounter = 0;
        //Load Teams and Time
        List<Game> resultList = TeamsAndTime.getTeamsAndGameTimeList(driver);

        //Load table stats
        String firstUrl = "https://www.flashscore.ru/match/";
        String secondUrl = "/#standings/table/overall";
        System.out.println("Start parce data. Count of records: " + resultList.size());
        for (Game game : resultList) {
            try {
                driver.get(firstUrl + game.getId() + secondUrl);
                WebElement element = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                String HTML = element.getAttribute("innerHTML");
                TableStats.getTableStats(game, HTML);
                ScoreAndMissedHomeAndAway.getTableStats(game, HTML);
            } catch (Exception e) {
                errorCounter++;
            }
        }
        System.out.println("End parce data. Count of records: " + (resultList.size() - errorCounter));
        return resultList;
    }
}