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


    public static List<Game> inputData(WebDriver driver) throws IOException {
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
                WebElement element = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                HTML = element.getAttribute("innerHTML");
            } catch (Exception exception) {
                game.setCheckTable(false);
                counter_data_dont_find_table++;
            }
            if (HTML != null) {
                TableStats.getTableStats(game, HTML);
                ScoreAndMissedHomeAndAway.getTableStats(game, HTML);
                counter_data_success_table_data++;
            }
        }
        driver.close();
        System.out.println("Data: " + counter_data_dont_find_table + " records don't have table page.");
        System.out.println("Data: " + (resultList.size() - counter_data_dont_find_table) + " input table data(place,goals)");
        System.out.println("Data: " + counter_data_success_table_data + " success output table data");


        //Load Coefficients
        for (Game game : resultList) {
            if (!game.isCheckTable()) {
                continue;
            }
            counter_data_start_coefficient++;
            try {
                driver.get(firstUrlСoefficient + game.getId() + secondUrlСoefficient);
                List<WebElement> element2 = driver.findElements(By.xpath("//div[@class=\"row___1rtP1QI undefined\"]"));
                Thread.sleep(2000);
                CoefficientData.loadCoefficients(element2, game);
                if (game.getCoefficients().size() != 0) {
                    counter_data_finish_coefficient++;
                } else {
                    counter_data_dont_find_coefficient++;
                }
            } catch (Exception exception) {
                counter_data_dont_find_coefficient++;
            }
        }
        System.out.println("Data: " + counter_data_start_coefficient + " records input data coefficient.");
        System.out.println("Data: " + counter_data_finish_coefficient + " success output coefficient data");
        System.out.println("Data: " + counter_data_dont_find_coefficient + " records don't have coefficient page.");
        driver.close();
        System.out.println("------------------------------------------------------");
        return resultList;
    }
}