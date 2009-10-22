=================================
EnumBuild: A Build DSL using Enum
=================================

author::
  Shoji Nishimura (shoji_n@muc.biglobe.ne.jp)
  
Contributers::
  Keiichi Iguchi
  Takatoshi Kitano

Introduction
============

Build system is a friend of developers. You know, Ant is the most famous 
and practical build system in Java Community. However, we must write build scripts 
in XML. We Java programmers are good at writing programs in Jave much better than XML.

EnumBuild is an experimental project to write a build script in Java.
We utilize a Enum class definition as a build DSL. The build script in EnumBuild
is a proper Java program and the description is quite familiar with you if you
know other build scripts such as Make and Ant.
Let's enjoy EnumBuild!

How to write a build script in EnumBuild
========================================

Simple Example
--------------

First of all, we show you a build script in EnumBuild.
This script is self-descriptive. We believe that you can easily imagine
how to write a build script in EnumBuild.

Code-Block:: Build.java

  public enum SampleBuild implements Target {
      /**
       *  COMPILE target:
       *  This target kicks JavacTask.
       */
      COMPILE() {
          @Override
          public void action() {
              /*
               * Kicking JavacTask.
               * Set required parameters as constractor arguments of the Args class.
               * The required parameters are defined as private final fields in the Args class.
               * Override optional parameters in the instance initializer of the Args class.
               * The optional parameters are defined as protected field and default value is set.
               */
              JavacTask.run(new Args("src", "dest")) {
                  {
                      debug = true;
                  }
              });
          }
      },
  
      /**
       *  TEST target:
       *  This target is depend on the COMPILE task.
       */
      TEST(COMPILE) {
          @Override
          public void action() {
              System.out.println("Test");
          }
      },
  
      /**
       *  PACKAGE target:
       *  This target is depend on the two targets: TEST and COMPILE.
       *  This target is annotated by the DefaultTarget annotation which describes 
       *  this target is a default target of this build script.
       */
      @DefaultTarget
      PACKAGE(TEST, COMPILE) {
          @Override
          public void action() {
              System.out.println("Package");
          }
      },
  
      /**
       *  CLEAN target
       *  
       */
      CLEAN() {
          @Override
          public void action() {
              System.out.println("Clean");
          }
      };
  
      // boiler plate code
      // if we can use an abstract enum syntax as [1], 
      // we can move the bellow code to EnumBuild abstract enum class.
      //
      // [1] Abstract Enums (Project Coins, additional proposal)
      //     http://mail.openjdk.java.net/pipermail/coin-dev/2009-May/001692.html
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


This build script is a Java program so that you can use full features of Java in
target definitions, task definitions and anything in the build script!
If you mistype a target name, javac reports it as a compile error!


How to run a build script
=========================

Currently we do not implement a command line tools, just provide a utility, BuildRunner.

BuildRunner runner = new BuildRunner();
runner.run(SampleBuild.PACKAGE);

