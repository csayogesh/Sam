package app.interfaces;

import app.Dimension;
import app.Goal;

import java.util.ArrayList;
import java.util.List;

public class SeeGoals extends Interface {
    private final Dimension dimension;

    public SeeGoals(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public boolean takeAction() {
        return false;
    }

    @Override
    public void displaySpecificMenuOptions() {
        List<Goal> ls = new ArrayList<>();
        dimension.fetchGoals(ls);
        for (Goal goal : ls) {
            actions.add(new GoalSelector(goal));
            System.out.println(actions.size() + ": " + goal.getDescription());
        }
        if (ls.size() == 0) {
            System.out.println("No goals found");
            continueInput = false;
        }
    }
}
