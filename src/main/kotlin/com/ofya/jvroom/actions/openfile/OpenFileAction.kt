package com.ofya.jvroom.actions.openfile

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager

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

  abstract override fun actionPerformed(event: AnActionEvent)

  override fun getActionUpdateThread(): ActionUpdateThread {
    return ActionUpdateThread.BGT
  }
}