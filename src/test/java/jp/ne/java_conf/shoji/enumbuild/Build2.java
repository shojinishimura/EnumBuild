/**
 * 
 */
package jp.ne.java_conf.shoji.enumbuild;

/**
 * @author shoji
 *
 */
public enum Build2 implements Target {
    TEST(Build1.TEST) {
        @Override
        public void action() {
            // TODO Auto-generated method stub

        }
    };

    private final Target[] dependents;

    Build2() {
        this(NO_DEPENDS);
    }

    Build2(Target... targets) {
        this.dependents = targets;
    }

    @Override
    public Target[] depends() {
        return dependents;
    }
}
