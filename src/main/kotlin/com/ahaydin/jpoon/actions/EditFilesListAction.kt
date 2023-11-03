package com.ahaydin.jpoon.actions

import com.ahaydin.jpoon.state.GlobalStateService
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JTextArea

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
        val globalStateService = project.service<GlobalStateService>()

        val filesList = globalStateService.getFiles()

        val filesListDialog = FilesListDialog(filesList)
        filesListDialog.show()

        if (filesListDialog.isOK) {
            globalStateService.setFiles(filesListDialog.getFilesList())
        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    private class FilesListDialog(filesList: MutableList<String>) : DialogWrapper(true) {

        private var textArea: JTextArea

        init {
            textArea = JTextArea(filesList.joinToString("\n"))
            textArea.wrapStyleWord = true
            textArea.lineWrap = true
            init()
            title = "JPoon"
        }

        override fun createCenterPanel(): JComponent {
            val scrollPane = JBScrollPane(textArea);
            scrollPane.preferredSize = Dimension(700, 300)
            return scrollPane
        }

        fun getFilesList(): List<String> {
            // TODO filter out which string coincide with a file
            return textArea.text.split("\n")
        }
    }
}