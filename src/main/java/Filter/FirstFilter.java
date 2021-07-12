package Filter;

import Model.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstFilter {
    static int count = 0;

    public List<Game> firstFilter(List<Game> list, WebDriver driver) {
        String firstUrl = "https://www.flashscore.ru/match/";
        String secondUrl = "/#standings/table/overall";

        for (Game game : list) {
            try {
                driver.get(firstUrl + game.getId() + secondUrl);
                WebElement element = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                String HTML = element.getAttribute("innerHTML");
                String compileHome = "<div class=\"row___1rtP1QI row___2EsvbFy selected___37fwRSu \">.*" + game.getHomeTeam() + ".*";
                String compileAway = "<div class=\"row___1rtP1QI row___2EsvbFy selected___37fwRSu \">.*" + game.getAwayTeam() + ".*";
                // System.out.println(HTML);

                Matcher matcherHomePlaceInTheTable = Pattern.compile(compileHome).matcher(HTML);
                Matcher matcherAwayPlaceInTheTable = Pattern.compile(compileAway).matcher(HTML);
                if (matcherHomePlaceInTheTable.find()) {
                    String homeTeam = String.valueOf(new StringBuilder(matcherHomePlaceInTheTable.group()));
                    int length = homeTeam.length();
                    homeTeam = homeTeam.substring(length - 90, length);
                    homeTeam.substring(homeTeam.indexOf('>') + 1, homeTeam.indexOf('<'));
                    game.setHomePlaceInTheTable(homeTeam.substring(homeTeam.indexOf('>') + 1, homeTeam.indexOf('<')));
                }
                if (matcherAwayPlaceInTheTable.find()) {
                    String awayTeam = String.valueOf(new StringBuilder(matcherAwayPlaceInTheTable.group()));
                    int length = awayTeam.length();
                    awayTeam = awayTeam.substring(length - 90, length);
                    awayTeam.substring(awayTeam.indexOf('>') + 1, awayTeam.indexOf('<'));
                    game.setAwayPlaceInTheTable(awayTeam.substring(awayTeam.indexOf('>') + 1, awayTeam.indexOf('<')));
                }
                if (game.getHomePlaceInTheTable() != null && game.getAwayPlaceInTheTable() != null &&
                        (Integer.valueOf(game.getHomePlaceInTheTable()) - Integer.valueOf(game.getAwayPlaceInTheTable()) >= 10)) {
                    game.setFirstFilterComplited(true);
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        return list;
    }
}
