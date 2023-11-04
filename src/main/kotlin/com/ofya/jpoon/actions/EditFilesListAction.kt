package com.ofya.jpoon.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.vfs.VirtualFileManager
import com.ofya.jpoon.GlobalStateService
import com.ofya.jpoon.ui.FilePathsEditDialog

class EditFilesListAction : AnAction() {
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

        val filePaths = globalStateService.getFilePaths()

        val filePathsEditDialog = FilePathsEditDialog(filePaths)
        filePathsEditDialog.show()

        if (filePathsEditDialog.isOK) {
            val editedFilePaths = filePathsEditDialog.getFilesList()
            globalStateService.setFilePaths(editedFilePaths.stream().filter {
                isProjectFile(it, projectBasePath)
            }.toList().take(9))
        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    private fun isProjectFile(filePath: String, projectBasePath: String): Boolean {
        val virtualFile = VirtualFileManager.getInstance().findFileByUrl("file://$filePath") ?: return false
        return virtualFile.path.startsWith(projectBasePath)
    }

}