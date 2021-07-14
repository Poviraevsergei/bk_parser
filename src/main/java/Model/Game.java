package Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private String homeTeam;
    private String awayTeam;
    private String gameTime;
    private String id;
    private String homePlaceInTheTable;
    private String awayPlaceInTheTable;
    private boolean firstFilterComplited;
    private boolean secondFilterComplited;
    private boolean thirdFilterComplited;
    private boolean fourthFilterComplited;
    private boolean fifthFilterComplited;
    private boolean homeBigger;
    private boolean awayBigger;
    private String allScoreHome;
    private String allScoreAway;
    private int scoredLastFiveMatchesHomeTeam;
    private int missedLastFiveMatchesHomeTeam;
    private int scoredLastFiveMatchesAwayTeam;
    private int missedLastFiveMatchesAwayTeam;
    private int scoredAllMatchesHomeTeam;
    private int missedAllMatchesHomeTeam;
    private int scoredAllMatchesAwayTeam;
    private int missedAllMatchesAwayTeam;
    private boolean missedChangedHome;
    private boolean missedChangedAway;
    private boolean scoreChangedHome;
    private boolean scoreChangedAway;
    private int differenceGoalHome;
    private int differenceGoalAway;
    private boolean checkTable = true;
    private List<String> coefficients = new ArrayList<>();
}