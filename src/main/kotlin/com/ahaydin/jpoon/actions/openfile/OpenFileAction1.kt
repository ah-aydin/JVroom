package com.ahaydin.jpoon.actions.openfile

import com.intellij.openapi.actionSystem.AnActionEvent

class OpenFileAction1 : OpenFileAction() {
    override fun actionPerformed(event: AnActionEvent) {
        openFile(event, 1)
    }
}