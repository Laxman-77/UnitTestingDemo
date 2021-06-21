package com.company;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws ClassNotFoundException {
        Result result = JUnitCore.runClasses(TestSuite1.class,TestSuite2.class);

        String testDir = "/test/com/company/";
        Suite.SuiteClasses suiteClasses = Class.forName("com.company.TestSuite1").getAnnotation(Suite.SuiteClasses.class);

        Class<?>[] classesInSuite = suiteClasses.value();
        System.out.println(classesInSuite.length);
        for(Class className: classesInSuite){
            System.out.println(className.getName());
            String fileName = testDir + className.getName().replace(".","/") +".java";

            System.out.println(fileName);

            Process pr;
            try{
                String[] blameCmd = {
                        "/bin/sh",
                        "-c",
                        "git blame --line-porcelain " + fileName + " | egrep \"author-mail\""
                };

                pr = Runtime.getRuntime().exec(blameCmd);

                InputStream is = pr.getInputStream();
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));

                try{
                    String line;
                    while((line = buf.readLine())!=null){
                        System.out.println(line);
                    }
                }
                finally {
                    System.out.println("gone");
                    is.close();
                    buf.close();
                }
            }
            catch (Throwable throwable){
                System.out.println("Maybe git blame failed.");
            }
            for(Method method:className.getMethods()){
               // System.out.println(method.getName());
            }
        }
        for(Failure failure:result.getFailures()){
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
