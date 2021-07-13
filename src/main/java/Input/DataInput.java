package Input;

import Model.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataInput {
    public static List<Game> getGamesList(WebDriver driver) throws IOException {
        List<Game> allGames = new ArrayList<>();
        driver.get("https://www.flashscore.ru/");
        WebElement element = driver.findElement(By.xpath("//div[@class=\"sportName soccer\"]"));
        for (String record : element.getAttribute("innerHTML").split("<div id=")) {

            Matcher matcherTime = Pattern.compile("\\d{2}:\\d{2}").matcher(record);
            Matcher matcherHome = Pattern.compile("<div class=\"event__participant event__participant--home\">.*").matcher(record);
            Matcher matcherAway = Pattern.compile("<div class=\"event__participant event__participant--away\">.*").matcher(record);

            if (matcherTime.find()) {
                Game game = new Game();
                game.setId(record.substring(5, 13));
                game.setGameTime(matcherTime.group());
                if (matcherHome.find()) {
                    String homeTeam = String.valueOf(new StringBuilder(matcherHome.group()).delete(0, 57));
                    game.setHomeTeam(homeTeam.substring(0, homeTeam.indexOf('<')));
                }
                if (matcherAway.find()) {
                    String awayTeam = String.valueOf(new StringBuilder(matcherAway.group()).delete(0, 57));
                    game.setAwayTeam(awayTeam.substring(0, awayTeam.indexOf('<')));
                }
                allGames.add(game);
            }
        }
        return allGames;
    }
}