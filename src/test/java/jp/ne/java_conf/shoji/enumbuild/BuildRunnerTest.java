package jp.ne.java_conf.shoji.enumbuild;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuildRunnerTest {

    @Test
    public void testRun1() {
        BuildRunner.run(SampleBuild.PACKAGE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRun2() throws Exception {
        BuildRunner.run(Build1.TEST);
    }

    @Test(expected = IllegalStateException.class)
    public void testRun3() throws Exception {
        BuildRunner.run(SelfRefBuild.SELF);
    }

    @Test
    public void testRun() throws Exception {
        BuildRunner.run(SampleBuild.CLEAN, SampleBuild.PACKAGE,
                SampleBuild.COMPILE);
    }
}
