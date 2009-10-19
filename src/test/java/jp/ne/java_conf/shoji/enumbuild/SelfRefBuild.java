package jp.ne.java_conf.shoji.enumbuild;

public enum SelfRefBuild implements Target {
    SELF {

        @Override
        public void action() {
        }

        @Override
        public Target[] depends() {
            return new Target[] { SELF };
        }
        
    };
}
