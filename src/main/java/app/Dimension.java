package app;

import java.util.*;

public class Dimension {
    private String id;
    private Dimension parent = null;
    private List<Dimension> subDimension = new ArrayList<>();
    private List<Goal> goals = new ArrayList<>();

    public Dimension(String id) {
        this.id = id;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
        goal.setDimension(this);
    }

    public void removeGoal(Goal goal) {
        goals.remove(goal);
    }

    public void addDimension(String dimension) {
        Dimension newDimension = new Dimension(dimension);
        newDimension.parent = this;
        subDimension.add(newDimension);
    }

    public String getId() {
        if (parent != null)
            return parent.getId() + "." + id;
        return id;
    }

    public String getRawId() {
        return id;
    }

    public List<Dimension> getSubDimension() {
        return subDimension;
    }
}
