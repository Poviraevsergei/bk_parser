package Output;

import Model.Game;

import java.util.ArrayList;
import java.util.List;

public class Stats {
    public static void checkCorrectMatches(List<Game> resultList) {
        List<Game> nullList = new ArrayList<>();
        List<Game> fiveList = new ArrayList<>();
        List<Game> fourList = new ArrayList<>();
        List<Game> threeList = new ArrayList<>();
        List<Game> twoList = new ArrayList<>();
        List<Game> oneList = new ArrayList<>();
        for (Game game : resultList) {
            int successCompleteFilters = 0;
            if (game.isFirstFilterComplited()) {
                successCompleteFilters++;
            }
            if (game.isSecondFilterComplited()) {
                successCompleteFilters++;
            }
            if (game.isThirdFilterComplited()) {
                successCompleteFilters++;
            }
            if (game.isFourthFilterComplited()) {
                successCompleteFilters++;
            }
            if (game.isFifthFilterComplited()) {
                successCompleteFilters++;
            }
            if (successCompleteFilters == 5) {
                fiveList.add(game);
            } else if (successCompleteFilters == 4) {
                fourList.add(game);
            } else if (successCompleteFilters == 3) {
                threeList.add(game);
            } else if (successCompleteFilters == 2) {
                twoList.add(game);
            } else if (successCompleteFilters == 1) {
                oneList.add(game);
            } else if (successCompleteFilters == 0) {
                nullList.add(game);
            }
        }
        System.out.println("----------------------RESULT----------------------");
        System.out.println("Out: " + resultList.size() + " all records.");
        System.out.println("Out: " + nullList.size() + " records don't have resulation success.");
        System.out.println("Out: " + oneList.size() + " records have 1 resulation success.");
        System.out.println("Out: " + twoList.size() + " records have 2 resulations success.");
        System.out.println("Out: " + threeList.size() + " records have 3 resulations success.");
        System.out.println("Out: " + fourList.size() + " records have 4 resulations success.");
        System.out.println("Out: " + fiveList.size() + " records have 5 resulations success.");
        if ((nullList.size() + oneList.size() + twoList.size() + threeList.size() + fourList.size() + fiveList.size()) != resultList.size()){
            System.err.println("Out: output count of records is different.");
        }
        System.out.println("----------------------END----------------------");
        if (fiveList.size() != 0) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("5 regulations completed SUCCESSFULLY");
            for (Game game : fiveList) {
                System.out.println(game);
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
        if (fourList.size() != 0) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("4 regulations completed SUCCESSFULLY");
            for (Game game : fourList) {
                System.out.println(game);
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
}