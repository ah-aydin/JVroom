package com.ofya.jpoon.actions.openfile

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFileManager
import com.ofya.jpoon.GlobalStateService
import com.ofya.jpoon.settings.SettingsState

abstract class OpenFileAction : AnAction() {
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
        val settingsState = SettingsState.getInstance()

        val fileToOpenPath = project.service<GlobalStateService>().getFilePathAt(index) ?: return
        val fileToOpen = VirtualFileManager.getInstance().findFileByUrl("file://$fileToOpenPath")

        if (settingsState.closeFilesAfterOpenFile) {
            val fileEditorManager = FileEditorManager.getInstance(project)
            fileEditorManager.openFiles.forEach { openFile ->
                run {
                    if (openFile == fileToOpen) {
                        return
                    }
                    fileEditorManager.closeFile(openFile)
                }
            }
        }

        if (fileToOpen != null) {
            val fileEditorManager = FileEditorManager.getInstance(project)
            fileEditorManager.openFile(fileToOpen, true)
        }
    }

    abstract override fun actionPerformed(event: AnActionEvent)

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}