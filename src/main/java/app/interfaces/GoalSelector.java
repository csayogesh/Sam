package app.interfaces;

import app.Goal;

public class GoalSelector extends Interface {
    private final Goal goal;

    public GoalSelector(Goal goal) {
        this.goal = goal;
    }

    @Override
    public boolean takeAction() {
        return false;
    }

    @Override
    public void displaySpecificMenuOptions() {
        System.out.println(goal.getDescription());
        actions.add(new ProcessGoal(goal, GoalAction.DELETE));
        System.out.println(actions.size() + ": Delete goal");
        actions.add(new ProcessGoal(goal, GoalAction.MARK_COMPLETE));
        System.out.println(actions.size() + ": Mark as complete");
    }
}
