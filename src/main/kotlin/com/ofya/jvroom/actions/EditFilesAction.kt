package com.ofya.jvroom.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFileManager
import com.ofya.jvroom.GlobalStateService
import com.ofya.jvroom.settings.SettingsState
import com.ofya.jvroom.ui.EditFilePathsPopupDialogUI
import com.ofya.jvroom.utils.closeAllFiles
import com.ofya.jvroom.utils.openFile

class EditFilesAction : AnAction() {
    override fun update(event: AnActionEvent) {
        val project = event.project
        if (project == null) {
            event.presentation.isEnabledAndVisible = false
            return
        }

        event.presentation.isEnabledAndVisible = true
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val projectBasePath = project.basePath ?: return
        val globalStateService = project.service<GlobalStateService>()
        val settingsState = SettingsState.getInstance()

        val filePaths = globalStateService.getFilePaths()

        val editFilePathsPopupDialogUI = EditFilePathsPopupDialogUI(filePaths)
        editFilePathsPopupDialogUI.show()

        if (editFilePathsPopupDialogUI.isOK) {
            val editedFilePaths = editFilePathsPopupDialogUI.getFilePaths()
            globalStateService.setFilePaths(editedFilePaths.stream().filter {
                isProjectFile(it, projectBasePath)
            }.toList())

            if (settingsState.reorderFilesAfterEdit) {
                closeAllFiles(event)
                for (i in 0 until (globalStateService.getFileCount())) {
                    openFile(event, i)
                }
            }

            if (settingsState.switchToSelectedFile && !editFilePathsPopupDialogUI.isEmpty()) {
                openFile(event, editFilePathsPopupDialogUI.getSelectedIndex())
            }
        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    private fun isProjectFile(filePath: String, projectBasePath: String): Boolean {
        return true
        val virtualFile = VirtualFileManager.getInstance().findFileByUrl("file://$filePath") ?: return false
        return virtualFile.path.startsWith(projectBasePath)
    }
}