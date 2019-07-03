package com.engineer.plugin

import com.engineer.plugin.actions.CalculateAction
import com.engineer.plugin.actions.RenameAction
import com.engineer.plugin.extensions.PhoenixExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PhoenixPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {

        project.extensions.create('phoenix', PhoenixExtension.class,project.objects)


        project.afterEvaluate {

            println()
            println "===================================PhoenixPlugin===============begin=================="
            println()

            if (!project.android) {
                throw new IllegalStateException('Must apply \'com.android.application\' or \'com.android.library\' first!')
            }

            RenameAction.doRenameAction(project)

            println()
            println "===================================PhoenixPlugin===============end=================="
            println()

        }

        project.gradle.buildFinished {
            CalculateAction.test(project)
        }

    }

}