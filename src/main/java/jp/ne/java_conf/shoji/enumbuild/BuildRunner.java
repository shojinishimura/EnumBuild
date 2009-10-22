package jp.ne.java_conf.shoji.enumbuild;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildRunner {

    public BuildRunner() {

    }

    public void run(Target... goals) {
        try {
            setUp();
            List<Target> invokeOrder = resolveInvokeOrder(goals);
            for (Target target : invokeOrder) {
                invoke(target);
            }
        } finally {
            tearDown();
        }
    }

    protected void setUp() {

    }

    protected void tearDown() {

    }

    protected void invoke(Target target) {
        target.action();
    }

    protected List<Target> resolveInvokeOrder(Target... goals) {
        return resolveInvokeOrderHelper(goals, new ArrayList<Target>(),
                new HashSet<Target>());
    }

    private List<Target> resolveInvokeOrderHelper(Target target,
            List<Target> invokeOrder, Set<Target> knownTargets) {
        if (target == null) {
            throw new IllegalArgumentException("target is null");
        }

        if (knownTargets.contains(target) && !invokeOrder.contains(target)) {
            throw new AssertionError(
                    String
                            .format(
                                    "Cyclic Dependency Detected: target %1$s, invoke order %2$s",
                                    target, invokeOrder));
        }
        knownTargets.add(target);

        resolveInvokeOrderHelper(target.depends(), invokeOrder, knownTargets);
        invokeOrder.add(target);

        return invokeOrder;
    }

    private List<Target> resolveInvokeOrderHelper(Target[] targets,
            List<Target> invokeOrder, Set<Target> knownTargets) {
        for (Target target : targets) {
            resolveInvokeOrderHelper(target, invokeOrder, knownTargets);
        }
        return invokeOrder;
    }

}
