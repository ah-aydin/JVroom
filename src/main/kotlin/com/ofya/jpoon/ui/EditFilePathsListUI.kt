package com.ofya.jpoon.ui

import com.intellij.ui.components.JBList
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.DefaultListModel

class EditFilePathsListUI(filePaths: List<String>, parent: EditFilePathsPopupDialogUI) : JBList<String>(filePaths) {
    private val DOUBLE_KEY_PRESS_MAX_DELAY_IN_MILLISECONDS = 300
    private var previousDeleteKeyPress: Long = 0;

    private val parent: EditFilePathsPopupDialogUI

    init {
        this.parent = parent
    }


    init {
        selectedIndex = 0
        addKeyListener(
            object : KeyAdapter() {
                override fun keyPressed(e: KeyEvent) {
                    processKeyEvent(e)
                }
            }
        )
    }

    override fun processKeyEvent(e: KeyEvent) {
        if (e.id != KeyEvent.KEY_PRESSED) {
            return
        }

        if (e.isShiftDown) {
            if (e.keyCode == KeyEvent.VK_J && selectedIndex + 1 < model.size) {
                val nextIndex = selectedIndex + 1
                val model = model as DefaultListModel
                val selectedElement = model.getElementAt(selectedIndex)
                val nextElement = model.getElementAt(nextIndex)

                model.set(selectedIndex, nextElement)
                model.set(nextIndex, selectedElement)

                selectedIndex++
            }
            if (e.keyCode == KeyEvent.VK_K && selectedIndex > 0) {
                val previousIndex = selectedIndex - 1
                val model = model as DefaultListModel
                val selectedElement = model.getElementAt(selectedIndex)
                val previousElement = model.getElementAt(previousIndex)

                model.set(selectedIndex, previousElement)
                model.set(previousIndex, selectedElement)

                selectedIndex--
            }
        } else {
            if (e.keyCode == KeyEvent.VK_J) {
                selectedIndex++;
            }
            if (e.keyCode == KeyEvent.VK_K) {
                selectedIndex--;
            }
            if (e.keyCode == KeyEvent.VK_D) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - previousDeleteKeyPress < DOUBLE_KEY_PRESS_MAX_DELAY_IN_MILLISECONDS) {
                    previousDeleteKeyPress = 0
                    val model = model as DefaultListModel
                    model.remove(selectedIndex)
                    selectedIndex = if (selectedIndex >= model.size) {
                        model.size - 1
                    } else {
                        selectedIndex
                    }
                }
                previousDeleteKeyPress = currentTime
            }
            if (e.keyCode == KeyEvent.VK_ENTER) {
                parent.performOKAction()
            }
            if (e.keyCode == KeyEvent.VK_ESCAPE) {
                parent.doCancelAction()
            }
        }
    }

    fun getFilePaths(): List<String> {
        val filePaths: MutableList<String> = mutableListOf()
        for (index in 0 until model.size) {
            filePaths.add(model.getElementAt(index))
        }
        return filePaths
    }
}
