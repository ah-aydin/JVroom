package com.ahaydin.jpoon.actions.openfile

import com.ahaydin.jpoon.state.GlobalStateService
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFileManager

open class OpenFileAction : AnAction() {
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

    protected fun openFile(event: AnActionEvent, index: Int) {
        val project = event.project ?: return

        val fileToOpenPath = project.service<GlobalStateService>().getFileAt(index) ?: return
        val fileToOpen = VirtualFileManager.getInstance().findFileByUrl("file://$fileToOpenPath")

        if (fileToOpen != null) {
            val fileEditorManager = FileEditorManager.getInstance(project)
            fileEditorManager.openFile(fileToOpen, true)
        }
    }

    override fun actionPerformed(event: AnActionEvent) {
        openFile(event, 0)
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}