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

    public Dimension addDimension(String dimension) {
        Dimension newDimension = new Dimension(dimension);
        newDimension.parent = this;
        subDimension.add(newDimension);
        try {
            PersistanceStorage.storeDimensions(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDimension;
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

    public Dimension getParent() {
        return parent;
    }

    public Dimension getMatchingSubDimension(String subDimension) {
        for (Dimension subDim : this.subDimension)
            if (subDim.id.equals(subDimension))
                return subDim;
        return null;
    }

    public boolean deleteDimension() {
        if (parent == null) {
            System.out.println("Cannot delete root Dimension");
            return false;
        }
        parent.subDimension.remove(this);
        try {
            PersistanceStorage.storeDimensions(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void fetchGoals(List<Goal> ls) {
        ls.addAll(goals);
        for(Dimension dimension:subDimension)
            dimension.fetchGoals(ls);
    }
}
