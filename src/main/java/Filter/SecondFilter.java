package Filter;

import Model.Game;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondFilter {
    public List<Game> secondFilter(List<Game> list, WebDriver driver) {
        List<Game> resultList = new ArrayList<>();
        String firstUrl = "https://www.flashscore.ru/match/";
        String secondUrl = "/#standings/table/overall";

        for (Game game : list) {
            try {
                driver.get(firstUrl + game.getId() + secondUrl);
                WebElement element = driver.findElement(By.xpath("//div[@class=\"rows___1BdItrT\"]"));
                String HTML = element.getAttribute("innerHTML");
                setScoredAndMissedHome(game, HTML);
                setScoredAndMissedAway(game, HTML);
                stats(game,resultList);
            } catch (Exception e) {
                // something
            }
        }
        return resultList;
    }

    public void stats(Game game, List<Game> resultList) {
        if (game.isHomeBigger()){
            if (game.isMissedChangedAway() && game.isScoreChangedAway() &&
                    game.isMissedChangedHome() && game.isScoreChangedHome()) {
                if ((game.getScoredLastFiveMatchesHomeTeam() - game.getMissedLastFiveMatchesHomeTeam() >= 0) &&
                        (game.getScoredLastFiveMatchesAwayTeam() - game.getMissedLastFiveMatchesAwayTeam()) < -2) {
                    game.setSecondFilterComplited(true);
                    resultList.add(game);
                }
            }
        }

        if(game.isAwayBigger()){
            if (game.isMissedChangedAway() && game.isScoreChangedAway() &&
                    game.isMissedChangedHome() && game.isScoreChangedHome()) {
                if ((game.getScoredLastFiveMatchesAwayTeam() - game.getMissedLastFiveMatchesAwayTeam() >= 0) &&
                        (game.getScoredLastFiveMatchesHomeTeam() - game.getMissedLastFiveMatchesHomeTeam()) < -2) {
                    game.setSecondFilterComplited(true);
                    resultList.add(game);
                }
            }
        }

    }

    public void setScoredAndMissedAway(Game game, String HTML) {
        Matcher matcherAway = Pattern.compile(game.getHomeTeam() + " - " + game.getAwayTeam() + "\n.*" + game.getAwayTeam() + ".*" + "\n.*" + game.getAwayTeam() + ".*" + "\n.*" + game.getAwayTeam() + ".*" + "\n.*" + game.getAwayTeam() + ".*" + "\n.*" + game.getAwayTeam() + ".*").matcher(HTML);

        if (matcherAway.find()) {
            String allScores = matcherAway.group();
            Matcher score = Pattern.compile("\\d+:\\d+.*").matcher(allScores);
            if (score.find()) {
                scoreMissAway(game, score.group());
                if (score.find()) {
                    scoreMissAway(game, score.group());
                    if (score.find()) {
                        scoreMissAway(game, score.group());
                        if (score.find()) {
                            scoreMissAway(game, score.group());
                            if (score.find()) {
                                scoreMissAway(game, score.group());
                            }
                        }
                    }
                }
            }
        }
    }

    public void setScoredAndMissedHome(Game game, String HTML) {
        Matcher matcherHome = Pattern.compile(game.getHomeTeam() + " - " + game.getAwayTeam() + "\n.*" + game.getHomeTeam() + ".*" + "\n.*" + game.getHomeTeam() + ".*" + "\n.*" + game.getHomeTeam() + ".*" + "\n.*" + game.getHomeTeam() + ".*" + "\n.*" + game.getHomeTeam() + ".*").matcher(HTML);

        if (matcherHome.find()) {
            String allScores = matcherHome.group();
            Matcher score = Pattern.compile("\\d+:\\d+.*").matcher(allScores);
            if (score.find()) {
                scoreMissHome(game, score.group());
                if (score.find()) {
                    scoreMissHome(game, score.group());
                    if (score.find()) {
                        scoreMissHome(game, score.group());
                        if (score.find()) {
                            scoreMissHome(game, score.group());
                            if (score.find()) {
                                scoreMissHome(game, score.group());
                            }
                        }
                    }
                }
            }
        }
    }

    public void scoreMissAway(Game game, String match) {
        Matcher away = Pattern.compile(game.getAwayTeam() + " - ").matcher(match);
        if (away.find()) {
            game.setScoredLastFiveMatchesAwayTeam(game.getScoredLastFiveMatchesAwayTeam() + Integer.parseInt(String.valueOf(match.charAt(0))));
            game.setMissedLastFiveMatchesAwayTeam(game.getMissedLastFiveMatchesAwayTeam() + Integer.parseInt(String.valueOf(match.charAt(2))));
            game.setScoreChangedAway(true);
            game.setMissedChangedAway(true);
        } else {
            game.setMissedLastFiveMatchesAwayTeam(game.getMissedLastFiveMatchesAwayTeam() + Integer.parseInt(String.valueOf(match.charAt(0))));
            game.setScoredLastFiveMatchesAwayTeam(game.getScoredLastFiveMatchesAwayTeam() + Integer.parseInt(String.valueOf(match.charAt(2))));
            game.setScoreChangedAway(true);
            game.setMissedChangedAway(true);
        }
    }

    public void scoreMissHome(Game game, String match) {
        Matcher home = Pattern.compile(game.getHomeTeam() + " - ").matcher(match);
        if (home.find()) {
            game.setScoredLastFiveMatchesHomeTeam(game.getScoredLastFiveMatchesHomeTeam() + Integer.parseInt(String.valueOf(match.charAt(0))));
            game.setMissedLastFiveMatchesHomeTeam(game.getMissedLastFiveMatchesHomeTeam() + Integer.parseInt(String.valueOf(match.charAt(2))));
            game.setScoreChangedHome(true);
            game.setMissedChangedHome(true);
        } else {
            game.setMissedLastFiveMatchesHomeTeam(game.getMissedLastFiveMatchesHomeTeam() + Integer.parseInt(String.valueOf(match.charAt(0))));
            game.setScoredLastFiveMatchesHomeTeam(game.getScoredLastFiveMatchesHomeTeam() + Integer.parseInt(String.valueOf(match.charAt(2))));
            game.setScoreChangedHome(true);
            game.setMissedChangedHome(true);
        }
    }
}