package com.ofya.jpoon.actions.openfile

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFileManager
import com.ofya.jpoon.GlobalStateService
import com.ofya.jpoon.settings.SettingsState

fun openFile(event: AnActionEvent, index: Int) {
    val project = event.project ?: return
    val settingsState = SettingsState.getInstance()
    val fileEditorManager = FileEditorManager.getInstance(project)

    val fileToOpenPath = project.service<GlobalStateService>().getFilePathAt(index) ?: return
    val fileToOpen = VirtualFileManager.getInstance().findFileByUrl("file://$fileToOpenPath")


    if (fileToOpen != null) {
        fileEditorManager.openFile(fileToOpen, true)
    }

    if (settingsState.closeFilesAfterOpenFile) {
        fileEditorManager.openFiles.forEach { openFile ->
            run {
                if (openFile == fileToOpen) {
                    return
                }
                fileEditorManager.closeFile(openFile)
            }
        }
    }
}
