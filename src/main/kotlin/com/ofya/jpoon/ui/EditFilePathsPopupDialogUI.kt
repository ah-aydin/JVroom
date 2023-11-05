package com.ofya.jpoon.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.DefaultListModel
import javax.swing.JComponent

class EditFilePathsPopupDialogUI(filePaths: List<String>) : DialogWrapper(true) {
    private val doubleKeyPressMaxDelayInMilliseconds = 300
    private var previousDeleteKeyPress: Long = 0;

    private val filePathsList: JBList<String>

    init {
        filePathsList = JBList<String>(filePaths)
        filePathsList.selectedIndex = 0
        filePathsList.addKeyListener(
            object : KeyAdapter() {
                override fun keyPressed(e: KeyEvent) {
                    processKeyEvent(e)
                }
            }
        )
        init()
        title = "JPoon Files"

    }

    override fun createCenterPanel(): JComponent {
        val scrollPane = JBScrollPane(filePathsList);
        scrollPane.preferredSize = Dimension(1000, 300)
        filePathsList.requestFocus()
        return scrollPane
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return filePathsList
    }

    fun processKeyEvent(e: KeyEvent) {
        if (e.id != KeyEvent.KEY_PRESSED) {
            return
        }

        if (e.isShiftDown) {
            if (e.keyCode == KeyEvent.VK_J && filePathsList.selectedIndex + 1 < filePathsList.model.size) {
                val nextIndex = filePathsList.selectedIndex + 1
                val model = filePathsList.model as DefaultListModel
                val selectedElement = model.getElementAt(filePathsList.selectedIndex)
                val nextElement = model.getElementAt(nextIndex)

                model.set(filePathsList.selectedIndex, nextElement)
                model.set(nextIndex, selectedElement)

                filePathsList.selectedIndex++
            }
            if (e.keyCode == KeyEvent.VK_K && filePathsList.selectedIndex > 0) {
                val previousIndex = filePathsList.selectedIndex - 1
                val model = filePathsList.model as DefaultListModel
                val selectedElement = model.getElementAt(filePathsList.selectedIndex)
                val previousElement = model.getElementAt(previousIndex)

                model.set(filePathsList.selectedIndex, previousElement)
                model.set(previousIndex, selectedElement)

                filePathsList.selectedIndex--
            }
        } else {
            if (e.keyCode == KeyEvent.VK_J) {
                filePathsList.selectedIndex++;
            }
            if (e.keyCode == KeyEvent.VK_K) {
                filePathsList.selectedIndex--;
            }
            if (e.keyCode == KeyEvent.VK_D) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - previousDeleteKeyPress < doubleKeyPressMaxDelayInMilliseconds) {
                    previousDeleteKeyPress = 0
                    val model = filePathsList.model as DefaultListModel
                    model.remove(filePathsList.selectedIndex)
                    filePathsList.selectedIndex = if (filePathsList.selectedIndex >= model.size) {
                        model.size - 1
                    } else {
                        filePathsList.selectedIndex
                    }
                }
                previousDeleteKeyPress = currentTime
            }
            if (e.keyCode == KeyEvent.VK_ENTER) {
                performOKAction()
            }
            if (e.keyCode == KeyEvent.VK_ESCAPE) {
                doCancelAction()
            }
        }
    }

    fun getFilePaths(): List<String> {
        val filePaths: MutableList<String> = mutableListOf()
        for (index in 0 until filePathsList.model.size) {
            filePaths.add(filePathsList.model.getElementAt(index))
        }
        return filePaths
    }

    fun getSelectedIndex(): Int {
        return filePathsList.selectedIndex
    }
}