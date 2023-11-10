package com.ofya.jvroom

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.fileEditor.FileEditorManager

class JVroomActionGroup : DefaultActionGroup() {
    override fun update(event: AnActionEvent) {
        val project = event.project
        if (project == null) {
            event.presentation.isEnabledAndVisible = false
        }

        val editor = FileEditorManager.getInstance(project!!).getSelectedEditor()
        if (editor == null) {
            event.presentation.isEnabledAndVisible = false
        }
        event.presentation.isEnabledAndVisible = true
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}