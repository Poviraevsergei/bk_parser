import Filter.FirstFilter;
import Filter.FourthFilter;
import Filter.SecondFilter;
import Filter.ThirdFilter;
import Input.DataInput;
import Model.Game;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

public class Parser {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Siarhey.Pavirayeu\\IdeaProjects\\bk_parser\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        List<Game> list = DataInput.inputData(driver);
        for (Game game : list) {
            System.out.println(game);
        }


        //First filter
        System.out.println("------------------------------------------------------\nFilter 1 Input: " + list.size());
        for (Game game : list) {
            FirstFilter.firstFilter(game);
        }
        System.out.println("Filter 1 has " + FirstFilter.f1_counter_dontHavePlace + " records without place information.");
        System.out.println("Filter 1 has " + FirstFilter.f1_counter_errorRecords + " error records.");
        System.out.println("Filter 1 has " + FirstFilter.f1_counter_filtered + " filtered records.");
        System.out.println("Filter 1 has " + FirstFilter.f1_counter_success + " success records.");
        if (list.size() != (FirstFilter.f1_counter_dontHavePlace + FirstFilter.f1_counter_errorRecords + FirstFilter.f1_counter_filtered + FirstFilter.f1_counter_success)) {
            System.err.println("Filter 1 has FAILED record status. Need " + list.size() + ", has "
                    + (FirstFilter.f1_counter_dontHavePlace + FirstFilter.f1_counter_errorRecords + FirstFilter.f1_counter_filtered + FirstFilter.f1_counter_success) + ".");
        } else {
            System.out.println("Filter 1 has SUCCESS record status");
        }
        if (FirstFilter.f1_counter_success != 0) {
            System.out.println("\nSuccess records:");
            for (Game game : list) {
                if (game.isFirstFilterComplited()) {
                    System.out.println(game);
                }
            }
        }

        //Second filter
        System.out.println("------------------------------------------------------\nFilter 2 Input: " + list.size());
        for (Game game : list) {
                SecondFilter.secondFilter(game);
        }
        System.out.println("Filter 2 has " + SecondFilter.f2_counter_dont_have_data + " records without score information.");
        System.out.println("Filter 2 has " + SecondFilter.f2_counter_errorRecords + " error records.");
        System.out.println("Filter 2 has " + SecondFilter.f2_counter_filtered + " filtered records.");
        int allSecondRecords = SecondFilter.f2_counter_dont_have_data + SecondFilter.f2_counter_errorRecords + SecondFilter.f2_counter_filtered + SecondFilter.f2_counter_success;
        System.out.println("Filter 2 has " + SecondFilter.f2_counter_success + " success records.");
        if (list.size() != allSecondRecords) {
            System.err.println("Filter 2 has FAILED record status. Need " + list.size() + ", has " + allSecondRecords + ".");
        } else {
            System.out.println("Filter 2 has SUCCESS record status");
        }
        if (SecondFilter.f2_counter_success != 0) {
            System.out.println("\nSuccess records:");
            for (Game game : list) {
                if (game.isSecondFilterComplited()) {
                    System.out.println(game);
                }
            }
        }

        //Third filter
        System.out.println("------------------------------------------------------\nFilter 3 Input: " + list.size());
        for (Game game : list) {
                ThirdFilter.thirdFilter(game);
        }
        System.out.println("Filter 3 has " + ThirdFilter.f3_counter_dont_have_data + " records without score information.");
        System.out.println("Filter 3 has " + ThirdFilter.f3_counter_errorRecords + " error records.");
        System.out.println("Filter 3 has " + ThirdFilter.f3_counter_filtered + " filtered records.");
        int allThirdRecords = ThirdFilter.f3_counter_dont_have_data + ThirdFilter.f3_counter_errorRecords + ThirdFilter.f3_counter_filtered + ThirdFilter.f3_counter_success;
        System.out.println("Filter 3 has " + ThirdFilter.f3_counter_success + " success records.");
        if (list.size() != allThirdRecords) {
            System.err.println("Filter 3 has FAILED record status. Need " + list.size() + ", has " + allThirdRecords + ".");
        } else {
            System.out.println("Filter 3 has SUCCESS record status");
        }
        if (ThirdFilter.f3_counter_success != 0) {
            System.out.println("\nSuccess records:");
            for (Game game : list) {
                if (game.isThirdFilterComplited()) {
                    System.out.println(game);
                }
            }
        }

        //Fourth filter
        System.out.println("------------------------------------------------------\nFilter 4 Input: " + list.size());
        for (Game game : list) {
                FourthFilter.checkCoefficient(game);
        }
        System.out.println("Filter 4 has " + (FourthFilter.f4_counter_TT + FourthFilter.f4_counter_didnt_coefficient) + " no data records ("
                + FourthFilter.f4_counter_TT + " false_false records and "
                + FourthFilter.f4_counter_didnt_coefficient + " don't have coefficient).");
        System.out.println("Filter 4 has " + FourthFilter.f4_counter_ErrorRecords + " error records.");
        System.out.println("Filter 4 has " + FourthFilter.f4_counter_filtered + " filtered records.");
        int allFourthRecords = (FourthFilter.f4_counter_TT + FourthFilter.f4_counter_didnt_coefficient) + FourthFilter.f4_counter_ErrorRecords + FourthFilter.f4_counter_filtered + FourthFilter.f4_counter_success;

        System.out.println("Filter 4 has " + FourthFilter.f4_counter_success + " success records.");
        if (list.size() != allFourthRecords) {
            System.err.println("Filter 4 has FAILED record status. Need " + list.size() + ", has "
                    + allFourthRecords + ".");
        } else {
            System.out.println("Filter 4 has SUCCESS record status");
        }
        if (FourthFilter.f4_counter_success != 0) {
            System.out.println("\nSuccess records:");
            for (Game game : list) {
                if (game.isFourthFilterComplited()) {
                    System.out.println(game);
                }
            }
        }
        for (Game game : list) {
            if (game.isFourthFilterComplited()) {
                System.out.println(game);
            }
        }
    }
}