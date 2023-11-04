package com.ahaydin.jpoon.actions

import com.ahaydin.jpoon.state.GlobalStateService
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager

class AddFileAction : AnAction() {

    override fun update(event: AnActionEvent) {
        val project = event.project
        if (project == null) {
            event.presentation.isEnabledAndVisible = false
            return
        }

        val editor = FileEditorManager.getInstance(project).getSelectedEditor()
        if (editor == null) {
            event.presentation.isEnabledAndVisible = false
            return
        }
        event.presentation.isEnabledAndVisible = true
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return

        val selectedFiles = FileEditorManager.getInstance(project).selectedFiles
        if (selectedFiles.isEmpty()) {
            return
        }
        val selectedFile = selectedFiles[0]

        project.service<GlobalStateService>().addFilePath(selectedFile.path)
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}