package app.interfaces;

import app.Goal;

public class ProcessGoal extends Interface {
    private final Goal goal;
    private final GoalAction action;

    public ProcessGoal(Goal goal, GoalAction action) {
        this.goal = goal;
        continueInput = false;
        this.action = action;
    }

    @Override
    public boolean takeAction() {
        switch (action) {
            case DELETE:
                goal.deleteGoal();
                break;
            case MARK_COMPLETE:
                goal.markComplete();
                break;
        }
        return true;
    }

    @Override
    public void displaySpecificMenuOptions() {

    }
}
