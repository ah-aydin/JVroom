package com.ofya.jpoon.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import javax.swing.JComponent

class EditFilePathsPopupDialogUI(filePaths: List<String>) : DialogWrapper(true) {
    private val editFilePathsListUI: EditFilePathsListUI

    init {
        editFilePathsListUI = EditFilePathsListUI(filePaths, this)
        init()
        title = "JPoon Files"

    }

    fun getFilePaths(): List<String> {
        return editFilePathsListUI.getFilePaths();
    }

    override fun createCenterPanel(): JComponent {
        val scrollPane = JBScrollPane(editFilePathsListUI);
        scrollPane.preferredSize = Dimension(1000, 300)
        editFilePathsListUI.requestFocus()
        return scrollPane
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return editFilePathsListUI
    }
}