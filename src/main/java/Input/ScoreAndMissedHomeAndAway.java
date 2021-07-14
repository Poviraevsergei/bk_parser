package Input;

import Model.Game;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreAndMissedHomeAndAway {
    public static void getTableStats(Game game, String HTML) throws IOException {
        try {
            setScoredAndMissedHome(game, HTML);
            setScoredAndMissedAway(game, HTML);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void setScoredAndMissedAway(Game game, String HTML) {
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

    public static void setScoredAndMissedHome(Game game, String HTML) {
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

    public static void scoreMissAway(Game game, String match) {
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

    public static void scoreMissHome(Game game, String match) {
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