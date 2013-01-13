package app.interfaces;

import app.Dimension;
import app.Goal;

public class GoalCreator extends Interface {
    private final Dimension dimension;

    public GoalCreator(Dimension dimension) {
        this.dimension = dimension;
        continueInput = false;
    }

    @Override
    public boolean takeAction() {
        System.out.println("Enter your goal");
        sc.nextLine();
        String goal = sc.nextLine();
        Goal goalObj = new Goal(goal);
        dimension.addGoal(goalObj);
        return false;
    }

    @Override
    public void displaySpecificMenuOptions() {
    }
}
