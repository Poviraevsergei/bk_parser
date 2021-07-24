package Input;

import Model.Game;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoefficientData {
    public static void loadCoefficients(List<WebElement> elementList, Game game) {
        String html;
        String homeCoefficient = "null";
        String drowCoefficient = "null";
        String awayCoefficient = "null";

        for (WebElement webElement : elementList) {
            html = webElement.getAttribute("innerHTML");
            Matcher matcherBk = Pattern.compile("Букмекер больше").matcher(html);
            if (matcherBk.find()) {
                continue;
            }
            Matcher matcherCoefficient = Pattern.compile("target=\"_blank\" title=.{12}").matcher(html);

            if (matcherCoefficient.find()) {
                homeCoefficient = getCoefficientFromHmtl(html, matcherCoefficient);
            }
            if (matcherCoefficient.find()) {
                drowCoefficient = getCoefficientFromHmtl(html, matcherCoefficient);
            }
            if (matcherCoefficient.find()) {
                awayCoefficient = getCoefficientFromHmtl(html, matcherCoefficient);
            }
            game.getCoefficients().add(homeCoefficient + " | " + drowCoefficient + " | " + awayCoefficient);
        }
    }

    static String getCoefficientFromHmtl(String html, Matcher matcher) {

        Matcher matcherCoefficientSecond = Pattern.compile("target=\"_blank\" title=.{22}").matcher(html);
        String coefficient = matcher.group().replace("target=\"_blank\" title=\"", "");
        if (!coefficient.matches("\\d{1}.\\d{2} » \\d{1}.\\d{2}")) {
            if (matcherCoefficientSecond.find()) {
                coefficient = matcherCoefficientSecond.group().replace("target=\"_blank\" title=\"\"><span class=\"\">", "");
                if (coefficient.length() != 11 && coefficient.length() != 4) {
                    try {
                        Matcher matcher_1 = Pattern.compile("\\d{1}.\\d{2} » \\d{1}.\\d{2}").matcher(coefficient);
                        Matcher matcher_2 = Pattern.compile("\\d{2}.\\d{2} » \\d{1}.\\d{2}").matcher(coefficient);
                        Matcher matcher_3 = Pattern.compile("\\d{1}.\\d{2} » \\d{2}.\\d{2}").matcher(coefficient);
                        Matcher matcher_4 = Pattern.compile("\\d{2}.\\d{2} » \\d{2}.\\d{2}").matcher(coefficient);
                        if (matcher_1.find()){
                            return matcher_1.group();
                        }
                        if (matcher_2.find()){
                            return matcher_2.group();
                        }
                        if (matcher_3.find()){
                            return matcher_3.group();
                        }
                        if (matcher_4.find()){
                            return matcher_4.group();
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
        }
        return coefficient;
    }
}