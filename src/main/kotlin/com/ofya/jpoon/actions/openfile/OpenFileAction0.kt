package com.ofya.jpoon.actions.openfile

import com.intellij.openapi.actionSystem.AnActionEvent

class OpenFileAction0 : OpenFileAction() {
    override fun actionPerformed(event: AnActionEvent) {
        openFile(event, 0)
    }
}