package com.ofya.jpoon.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JTextArea

class FilePathsEditDialog(filesList: MutableList<String>) : DialogWrapper(true) {

    private var textArea: JTextArea

    init {
        textArea = JTextArea(filesList.joinToString("\n"))
        textArea.wrapStyleWord = true
        textArea.lineWrap = true
        init()
        title = "JPoon Files"
    }

    override fun createCenterPanel(): JComponent {
        val scrollPane = JBScrollPane(textArea);
        scrollPane.preferredSize = Dimension(1000, 300)
        return scrollPane
    }

    fun getFilesList(): List<String> {
        return textArea.text.split("\n")
    }
}
