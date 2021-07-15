package Filter;

import Model.Game;

public class FifthFilter {
    public static int f5_counter_TT = 0;
    public static int f5_counter_didnt_coefficient = 0;
    public static int f5_counter_errorRecords = 0;
    public static int f5_counter_filtered = 0;
    public static int f5_counter_success = 0;

    public static void fifthFilter(Game game) {
        int counter_success = 0;
        String home = "0.0";
        String away = "0.0";
        String homeBefore = "0.0";
        String awayBefore = "0.0";
        try {
            if ((game.isAwayBigger() || game.isHomeBigger())) {
                if (game.getCoefficients().size() != 0) {
                    for (String text : game.getCoefficients()) {
                        text = text.replace("[", "").replace("]", "");
                        String lines[] = text.split(", ");
                        for (String line : lines) {
                            String[] coefficients = line.split(" \\| ");
                            String[] coefficientsBefore = line.split(" \\| ");
                            for (int i = 0; i < coefficients.length; i++) {
                                if (coefficients[i].length() != 4 && coefficients[i].length() != 5) {
                                    String temp[] = coefficients[i].split(" » ");
                                    coefficients[i] = temp[1];
                                    coefficientsBefore[i] = temp[0];
                                }
                            }
                            homeBefore = coefficientsBefore[0];
                            awayBefore = coefficientsBefore[2];
                            home = coefficients[0];
                            away = coefficients[2];
                            if (game.isHomeBigger() && ((Double.parseDouble(homeBefore) - Double.parseDouble(home)) >= 0)) {
                                counter_success++;
                            } else if (game.isAwayBigger() && ((Double.parseDouble(awayBefore) - Double.parseDouble(away)) >= 0)) {
                                counter_success++;
                            }
                            if (game.getCoefficients().size() == 5 && counter_success >= 3) {
                                game.setFifthFilterComplited(true);
                            } else if (game.getCoefficients().size() == 4 && counter_success >= 2) {
                                game.setFifthFilterComplited(true);
                            } else if (game.getCoefficients().size() == 3 && counter_success >= 2) {
                                game.setFifthFilterComplited(true);
                            } else if (game.getCoefficients().size() == 2 && counter_success >= 1) {
                                game.setFifthFilterComplited(true);
                            }
                        }
                    }
                    if (!game.isFifthFilterComplited()) {
                        f5_counter_filtered++;
                    }
                } else {
                    f5_counter_didnt_coefficient++;
                }
            } else {
                f5_counter_TT++;
            }
            if (game.isFifthFilterComplited()) {
                f5_counter_success++;
            }
        } catch (Exception exception) {
            System.err.println(exception);
            f5_counter_errorRecords++;
        }
    }
}