package jp.ne.java_conf.shoji.enumbuild;

public interface Target {
    public Target[] depends();
    public void action();
    public static final Target[] NO_DEPENDS = new Target[0];
}
