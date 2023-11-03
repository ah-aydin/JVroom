package com.ahaydin.jpoon.actions

import com.ahaydin.jpoon.state.GlobalStateService
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.Messages

class DisplayFilesAction : AnAction() {

    override fun update(event: AnActionEvent) {
        val project = event.project
        event.presentation.setEnabledAndVisible(project != null)
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return

        val title = "File List"
        val message = event.project?.service<GlobalStateService>()?.getFilesAsString()

        Messages.showMessageDialog(project, message, title, Messages.getInformationIcon())
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}