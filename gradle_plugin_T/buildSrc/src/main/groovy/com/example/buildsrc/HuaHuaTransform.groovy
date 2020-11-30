package com.example.buildsrc

import com.android.annotations.NonNull
import com.android.build.api.transform.Format;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils

class HuaHuaTransform extends Transform{
    @Override
    String getName() {
        // 在Gradle -》other 下生成 transformClassesWithHuaForDebug / ....
        return "hua"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        // 处理那些文件
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        // 处理的项目范围, 可以自己灵活配置
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    boolean isIncremental() {
        return false
    }

    // 有默认行为是把文件扔掉，需要重写transform 方法
    @Override
    public void transform(@NonNull TransformInvocation transformInvocation)
            throws TransformException, InterruptedException, IOException {
        // super.transform(transformInvocation)  默认是空实现

        // 下面实现原封不动搬过去的过程
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider;
        inputs.each {
            it.jarInputs.each {
                File dest = outputProvider.getContentLocation(it.name,it.contentTypes,it.scopes,
                        Format.JAR);
                println("Jar : ${it.file} dest :${dest}")
                FileUtils.copyFile(it.file,dest)
            }

            it.directoryInputs.each {
                File dest = outputProvider.getContentLocation(it.name,it.contentTypes,it.scopes,
                        Format.DIRECTORY)
                println("Dir : ${it.file} dest :${dest}")
                FileUtils.copyDirectory(it.file,dest)
            }
        }

        // 改字节码文件需要涉及的工具
        // javassist
        // ASM
    }
}
