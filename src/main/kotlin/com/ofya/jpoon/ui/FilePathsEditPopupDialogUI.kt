package com.ofya.jpoon.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import javax.swing.JComponent

class FilePathsEditPopupDialogUI(filePaths: List<String>) : DialogWrapper(true) {
    private val filePathsEditListUI: FilePathsEditListUI

    init {
        filePathsEditListUI = FilePathsEditListUI(filePaths)
        init()
        title = "JPoon Files"

    }

    fun getFilePaths(): List<String> {
        return filePathsEditListUI.getFilePaths();
    }

    override fun createCenterPanel(): JComponent? {
        val scrollPane = JBScrollPane(filePathsEditListUI);
        scrollPane.preferredSize = Dimension(1000, 300)
        filePathsEditListUI.requestFocus()
        return scrollPane
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return filePathsEditListUI
    }
}