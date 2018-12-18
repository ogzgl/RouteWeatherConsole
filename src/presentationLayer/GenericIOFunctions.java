package presentationLayer;

import java.util.Scanner;

public class GenericIOFunctions {
    public String stringInputHandler(String infoMsg) {
        System.out.println(infoMsg + ": ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
