package app.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Interface {
    protected List<Interface> actions = new ArrayList<>();

    public final void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            displaySpecificMenuOptions();
            actions.add(new ExitInterface());
            System.out.println((actions.size()) + ": Exit");
            int input = sc.nextInt();
            if (input == actions.size())
                break;
            if (input <= actions.size() && input > 0)
                actions.get(input - 1).handleUserInput();
            else System.out.println("Please enter valid input");
            actions.clear();
        }
        sc.close();
    }

    public abstract void displaySpecificMenuOptions();
}
