package com.example.publishplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyClass implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        def publishExt = project.getExtensions().create("publishV",PublishExt.class);
        project.afterEvaluate{
            System.out.print("publish version is ");
            System.out.println(publishExt.publishVer);
        }
    }
}