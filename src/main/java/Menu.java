import com.sun.tools.jdeprscan.scan.Scan;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private String printChoices() {
        return "Hey there, these are your options:\n" +
                "0 - show all games\n" +
                "1 - show games rated higher than...\n" +
                "2 -show games with category of...\n" +
                " Your choice: ";
    }

    public String menu() {
        System.out.println(printChoices());
        String choice = scanner.nextLine();
        String output = "select * from ";

        while (choice == null || (!"0".equals(choice) && !"1".equals(choice) && !"2".equals(choice))) {
            System.out.println("Choose again" + printChoices());
            choice = scanner.nextLine();
        }

        switch (choice) {
            case "0":
                output += "games_table";
                break;
            case "1":
                output += "(SELECT * FROM games_table order by rating DESC)";
                break;
            case "2":
                System.out.println("Choose rating 1 - 10");
                int reating = scanner.nextInt();
                output += "games where rating > " + reating;
                break;

        }
        return output + ";";
    }
}
