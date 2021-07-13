package Input;

import Model.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoefficientData {
    public static void setCoefficient(String HTML2, Game game) {
        Matcher matcherAwayPlaceInTheTable = Pattern.compile("\\d{1}.\\d{2} » \\d{1}.\\d{2}").matcher(HTML2);
        String resultCoefficient = "X | X | X";
        if (matcherAwayPlaceInTheTable.find()) {
            resultCoefficient = matcherAwayPlaceInTheTable.group().replace("BY-HM\" target=\"_blank\" title=\"", "");
        }
        if (matcherAwayPlaceInTheTable.find()) {
            resultCoefficient = resultCoefficient + " | " + matcherAwayPlaceInTheTable.group().replace("BY-HM\" target=\"_blank\" title=\"", "");
        }
        if (matcherAwayPlaceInTheTable.find()) {
            resultCoefficient = resultCoefficient + " | " + matcherAwayPlaceInTheTable.group().replace("BY-HM\" target=\"_blank\" title=\"", "");
        }
        game.getCoefficients().add(resultCoefficient);
    }
}