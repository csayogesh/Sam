package app.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Interface {
    protected static Scanner sc = new Scanner(System.in);
    protected List<Interface> actions = new ArrayList<>();

    public final void handleUserInput() {
        while (true) {
            actions.add(new ExitInterface());
            System.out.println((actions.size()) + ": Exit");
            displaySpecificMenuOptions();
            int input = sc.nextInt();
            if (input == 1)
                break;
            if (input <= actions.size() && input > 0) {
                actions.get(input - 1).takeAction();
                actions.get(input - 1).handleUserInput();
            } else System.out.println("Please enter valid input");
            actions.clear();
        }
    }

    public abstract void takeAction();

    public abstract void displaySpecificMenuOptions();
}
