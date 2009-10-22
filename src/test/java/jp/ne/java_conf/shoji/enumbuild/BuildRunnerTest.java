package jp.ne.java_conf.shoji.enumbuild;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuildRunnerTest {

    @Test
    public void testRun1() {
        BuildRunner testObj = new BuildRunner();
        testObj.run(SampleBuild.PACKAGE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRun2() throws Exception {
        BuildRunner testObj = new BuildRunner();
        testObj.run(Build1.TEST);
    }

    @Test(expected = AssertionError.class)
    public void testRun3() throws Exception {
        BuildRunner testObj = new BuildRunner();
        testObj.run(SelfRefBuild.SELF);
    }

    @Test
    public void testRun() throws Exception {
        BuildRunner testObj = new BuildRunner();
        testObj
                .run(SampleBuild.CLEAN, SampleBuild.PACKAGE,
                        SampleBuild.COMPILE);
    }
}
