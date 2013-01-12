package app;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Goal implements Comparable<Goal> {
    private String description;
    private long deadline;
    private long completionTime = -1;
    private Dimension dimension;

    public Goal(String description) {
        this.description = description;
        deadline = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7);
    }

    @Override
    public int compareTo(Goal goal) {
        return Long.compare(deadline, goal.deadline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(description, goal.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }


    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public void setCompletionTime(long completionTime) {
        this.completionTime = completionTime;
    }
}
