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
    public static List<Game> inputData(WebDriver driver) throws IOException {
        List<Game> resultList = new ArrayList<>();

        //Load Teams and Time
        resultList = TeamsAndTime.getTeamsAndGameTimeList(driver);

        //Load
        return resultList;
    }
}