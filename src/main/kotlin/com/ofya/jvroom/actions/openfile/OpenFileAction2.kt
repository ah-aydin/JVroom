package com.ofya.jvroom.actions.openfile

import com.intellij.openapi.actionSystem.AnActionEvent

class OpenFileAction2 : OpenFileAction() {
    override fun actionPerformed(event: AnActionEvent) {
        openFile(event, 2)
    }
}