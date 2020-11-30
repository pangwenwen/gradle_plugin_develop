package com.example.buildsrc
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project;


public class HuaHuaPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        def extention = project.extensions.create("huahua",HuaHuaPluginExtention);
        project.afterEvaluate {
            print("Hello ${extention.name} !");
        }

//        def transform = new HuaHuaTransform()
//        // 拿到build.gradle 中名为android 配置的扩展
//        def baseExtension = project.extensions.getByType(BaseExtension)
//        baseExtension.registerTransform(transform)
    }
}