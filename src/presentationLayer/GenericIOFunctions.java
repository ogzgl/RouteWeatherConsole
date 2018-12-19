package presentationLayer;

import java.util.Scanner;

public class GenericIOFunctions {
    public String stringInputHandler(String infoMsg) {
        System.out.println(infoMsg + ": ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (!input.equals("quit")) {
            return input;
        } else {
            System.exit(1);
            return "";
        }
    }
}
