package com.ofya.jvroom.utils

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFileManager
import com.ofya.jvroom.ProjectStateService
import com.ofya.jvroom.globalsettings.SettingsState

fun openFile(event: AnActionEvent, index: Int) {
  val project = event.project ?: return
  val settingsState = SettingsState.getInstance()
  val fileEditorManager = FileEditorManager.getInstance(project)

  val fileToOpenPath = project.service<ProjectStateService>().getFilePathAt(index) ?: return
  val fileToOpen = VirtualFileManager.getInstance().findFileByUrl("file://$fileToOpenPath") ?: return

  fileEditorManager.openFile(fileToOpen, true)
}

fun closeAllFiles(event: AnActionEvent) {
  val project = event.project ?: return
  val fileEditorManager = FileEditorManager.getInstance(project)

  fileEditorManager.openFiles.forEach { openFile -> run { fileEditorManager.closeFile(openFile) } }
}
