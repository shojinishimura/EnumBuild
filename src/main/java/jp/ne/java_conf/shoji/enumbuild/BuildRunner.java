package jp.ne.java_conf.shoji.enumbuild;

import java.util.ArrayList;
import java.util.List;

public class BuildRunner {

    private final Target[] goals;

    public BuildRunner(Target goal) {
        this.goals = new Target[] { goal };
    }

    public BuildRunner(Target... goals) {
        this.goals = goals;
    }

    public static void run(Target goal) {
        new BuildRunner(goal).invoke();
    }
    
    public static void run(Target...goals) {
        new BuildRunner(goals).invoke();
    }

    public void invoke() {
        invoke(goals);
    }

    private List<Target> invoked = new ArrayList<Target>();
    private List<Target> visited = new ArrayList<Target>();

    private boolean isVisited(Target target) {
        return visited.contains(target);
    }

    private boolean isInvoked(Target target) {
        return invoked.contains(target);
    }

    protected void invoke(Target goal) {
        if (goal == null) {
            throw new IllegalArgumentException("Target is null: "
                    + invokedList());
        }

        if (isVisited(goal) && !isInvoked(goal)) {
            throw new IllegalStateException(
                    String
                            .format(
                                    "Cycric Dependency Detected: current target %1$s, invokeLiks %2$s",
                                    goal, invokedList()));
        }
        visited.add(goal);

        invoke(goal.depends());

        if (!invoked.contains(goal)) {
            goal.action();
            invoked.add(goal);
        }
    }

    protected void invoke(Target... goals) {
        for (Target target : goals) {
            invoke(target);
        }
    }

    private String invokedList() {
        StringBuilder sb = new StringBuilder();
        for (Target target : invoked) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(target);
        }
        return sb.toString();
    }
}
