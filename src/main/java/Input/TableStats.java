package Input;

import Model.Game;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableStats {
    public static void getTableStats(Game game, String HTML) throws IOException {
        try {
            String compileHome = "<div class=\"row___1rtP1QI row___2EsvbFy selected___37fwRSu \">.*" + game.getHomeTeam() + ".*";
            String compileAway = "<div class=\"row___1rtP1QI row___2EsvbFy selected___37fwRSu \">.*" + game.getAwayTeam() + ".*";

            Matcher matcherHomePlaceInTheTable = Pattern.compile(compileHome).matcher(HTML);
            Matcher matcherAwayPlaceInTheTable = Pattern.compile(compileAway).matcher(HTML);
            if (matcherHomePlaceInTheTable.find()) {
                String homeTeam = String.valueOf(new StringBuilder(matcherHomePlaceInTheTable.group()));
                int length = homeTeam.length();
                String allScoreHome = homeTeam.substring(length - 150, length);
                allScoreHome = allScoreHome.substring(allScoreHome.indexOf('>') + 1, allScoreHome.indexOf('<'));
                if (allScoreHome.length() < 3) {
                    allScoreHome = String.valueOf(new StringBuilder(matcherHomePlaceInTheTable.group())).substring(length - 250, length);
                    allScoreHome = allScoreHome.substring(allScoreHome.indexOf('>') + 1, allScoreHome.indexOf('<'));
                }
                game.setAllScoreHome(allScoreHome);
                homeTeam = homeTeam.substring(length - 90, length);
                homeTeam.substring(homeTeam.indexOf('>') + 1, homeTeam.indexOf('<'));
                game.setHomePlaceInTheTable(homeTeam.substring(homeTeam.indexOf('>') + 1, homeTeam.indexOf('<')));
            }
            if (matcherAwayPlaceInTheTable.find()) {
                String awayTeam = String.valueOf(new StringBuilder(matcherAwayPlaceInTheTable.group()));
                int length = awayTeam.length();
                String allScoreAway = awayTeam.substring(length - 150, length);
                allScoreAway = allScoreAway.substring(allScoreAway.indexOf('>') + 1, allScoreAway.indexOf('<'));
                if (allScoreAway.length() < 3) {
                    allScoreAway = String.valueOf(new StringBuilder(matcherAwayPlaceInTheTable.group())).substring(length - 250, length);
                    allScoreAway = allScoreAway.substring(allScoreAway.indexOf('>') + 1, allScoreAway.indexOf('<'));
                }
                game.setAllScoreAway(allScoreAway);
                awayTeam = awayTeam.substring(length - 90, length);
                awayTeam.substring(awayTeam.indexOf('>') + 1, awayTeam.indexOf('<'));
                game.setAwayPlaceInTheTable(awayTeam.substring(awayTeam.indexOf('>') + 1, awayTeam.indexOf('<')));
            }
        } catch (Exception e) {
            System.out.println("Exception TableStats");
        }
    }
}




