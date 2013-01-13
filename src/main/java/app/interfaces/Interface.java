package app.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Interface {
    protected static Scanner sc = new Scanner(System.in);
    protected List<Interface> actions = new ArrayList<>();
    protected boolean continueInput = true;

    public final void handleUserInput() {
        while (true) {
            if (!continueInput)
                break;
            actions.add(new ExitInterface());
            System.out.println((actions.size()) + ": Exit");
            displaySpecificMenuOptions();
            int input = sc.nextInt();
            if (input == 1)
                break;
            if (input <= actions.size() && input > 0) {
                boolean breakForward = actions.get(input - 1).takeAction();
                if (breakForward)
                    break;
                actions.get(input - 1).handleUserInput();
            } else System.out.println("Please enter valid input");
            actions.clear();
        }
    }

    public abstract boolean takeAction();

    public abstract void displaySpecificMenuOptions();
}
