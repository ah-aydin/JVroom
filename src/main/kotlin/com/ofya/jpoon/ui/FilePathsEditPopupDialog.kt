package com.ofya.jpoon.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import javax.swing.JComponent

class FilePathsEditPopupDialog(filePaths: List<String>) : DialogWrapper(true) {
    private val filePathsEditList: FilePathsEditList

    init {
        filePathsEditList = FilePathsEditList(filePaths)
        init()
        title = "JPoon Files"

    }

    fun getFilePaths(): List<String> {
        return filePathsEditList.getFilePaths();
    }

    override fun createCenterPanel(): JComponent? {
        val scrollPane = JBScrollPane(filePathsEditList);
        scrollPane.preferredSize = Dimension(1000, 300)
        return scrollPane
    }
}