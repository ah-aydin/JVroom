package com.ofya.jvroom.actions.openfile

import com.intellij.openapi.actionSystem.AnActionEvent
import com.ofya.jvroom.utils.openFile

class OpenFileAction4 : OpenFileAction() {
  override fun actionPerformed(event: AnActionEvent) {
    openFile(event, 4)
  }
}