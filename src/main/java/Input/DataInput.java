package Input;

import Model.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class DataInput {
    private static int counter_data_dont_find_table;
    private static int counter_data_dont_find_coefficient;
    private static int counter_data_success_table_data;
    private static int counter_data_start_coefficient;
    private static int counter_data_finish_coefficient;
    private static int counter_data_error_coefficient;
    private static int counter_data_error_table;


    public static List<Game> inputData(WebDriver driver) throws IOException, InterruptedException {
        String firstUrl = "https://www.flashscore.ru/match/";
        String secondUrlTable = "/#standings/table/overall";
        String firstUrlСoefficient = "https://www.flashscorekz.com/match/";
        String secondUrlСoefficient = "/#odds-comparison/1x2-odds/full-time";

        //Load Teams and Time
        List<Game> resultList = TeamsAndTime.getTeamsAndGameTimeList(driver);
        System.out.println("Data: Count of games " + resultList.size() + ".");

        //Load table stats
        for (Game game : resultList) {
            try {
                driver.get(firstUrl + game.getId() + secondUrlTable);
            } catch (Exception exception) {
                System.err.println("Can't open the page -> " + exception);
            }
            String HTML = null;
            try {
                if (driver.getCurrentUrl().endsWith("/#match-summary")) {
                    game.setCheckTable(false);
                    counter_data_dont_find_table++;
                    continue;
                }
                Thread.sleep(2000);
                WebElement element = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                HTML = element.getAttribute("innerHTML");
            } catch (Exception exception) {
                counter_data_error_table++;
            }
            if (HTML != null) {
                TableStats.getTableStats(game, HTML);
                ScoreAndMissedHomeAndAway.getTableStats(game, HTML);
                counter_data_success_table_data++;
            }
        }
        System.out.println("Data: " + counter_data_dont_find_table + " records don't have table page.");
        System.out.println("Data: " + (resultList.size() - counter_data_dont_find_table) + " input table data(place,goals)");
        System.out.println("Data: " + counter_data_success_table_data + " success output table data");
        System.out.println("Data: " + counter_data_error_table + " ERROR table data");

        //Load Coefficients
        for (Game game : resultList) {
            if (!game.isCheckTable() || driver.getCurrentUrl().endsWith("/#match-summary")) {
                counter_data_dont_find_coefficient++;
                continue;
            }
            counter_data_start_coefficient++;
            try {
                driver.get(firstUrlСoefficient + game.getId() + secondUrlСoefficient);
                Thread.sleep(2000);
                List<WebElement> element2 = driver.findElements(By.xpath("//div[@class=\"row___1rtP1QI undefined\"]"));
                CoefficientData.loadCoefficients(element2, game);
                if (game.getCoefficients().size() != 0) {
                    counter_data_finish_coefficient++;
                }
            } catch (Exception exception) {
                counter_data_error_coefficient++;
            }
        }
        System.out.println("Data: " + counter_data_start_coefficient + " records input data coefficient.");
        System.out.println("Data: " + counter_data_finish_coefficient + " success output coefficient data");
        System.out.println("Data: " + counter_data_dont_find_coefficient + " records don't have coefficient page.");
        System.out.println("Data: " + counter_data_error_coefficient + " records have coefficient error.");
        if (counter_data_start_coefficient != counter_data_finish_coefficient + counter_data_error_coefficient) {
            System.out.println("SOMETHING WRONG WITH COEFFICIENT!!!!!!!");
        }
        driver.close();
        System.out.println("------------------------------------------------------");
        return resultList;
    }
}