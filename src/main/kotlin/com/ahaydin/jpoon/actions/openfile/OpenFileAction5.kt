package com.ahaydin.jpoon.actions.openfile

import com.intellij.openapi.actionSystem.AnActionEvent

class OpenFileAction5 : OpenFileAction() {
    override fun actionPerformed(event: AnActionEvent) {
        openFile(event, 5)
    }
}