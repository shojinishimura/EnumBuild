/**
 * 
 */
package jp.ne.java_conf.shoji.enumbuild;

/**
 * @author shoji
 * 
 */
public enum Build1 implements Target {
    TEST(Build2.TEST) {
        @Override
        public void action() {
            // TODO Auto-generated method stub

        }
    };

    private final Target[] dependents;

    Build1() {
        this(NO_DEPENDS);
    }

    Build1(Target... targets) {
        this.dependents = targets;
    }

    @Override
    public Target[] depends() {
        return dependents;
    }
}
