package jp.ne.java_conf.shoji.enumbuild;

public enum SampleBuild implements Target {
    COMPILE() {
        @Override
        public void action() {
            System.out.println("Compile");
        }
    },

    TEST(COMPILE) {
        @Override
        public void action() {
            System.out.println("Test");
        }
    },

    PACKAGE(TEST, COMPILE) {
        @Override
        public void action() {
            System.out.println("Package");
        }
    },

    CLEAN() {
        @Override
        public void action() {
            System.out.println("Clean");
        }
    };

    private final Target[] dependents;

    SampleBuild() {
        this(NO_DEPENDS);
    }

    SampleBuild(Target... targets) {
        this.dependents = targets;
    }

    @Override
    public Target[] depends() {
        return dependents;
    }
}
