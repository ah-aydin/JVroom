package com.ofya.jpoon.ui

import com.intellij.ui.components.JBList
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.DefaultListModel

class EditFilePathsListUI(filePaths: List<String>) : JBList<String>(filePaths) {
    private val DOUBLE_KEY_PRESS_MAX_DELAY_IN_MILLISECONDS = 300
    private var previousDeleteKeyPress: Long = 0;


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
            if ((e.keyCode == KeyEvent.VK_J || e.keyCode == KeyEvent.VK_DOWN) && selectedIndex + 1 < model.size) {
                val model = model as DefaultListModel
                val element1 = model.getElementAt(selectedIndex)
                val element2 = model.getElementAt(selectedIndex + 1)

                model.set(selectedIndex, element2)
                model.set(selectedIndex + 1, element1)

                selectedIndex++
            }
            if ((e.keyCode == KeyEvent.VK_K || e.keyCode == KeyEvent.VK_UP) && selectedIndex > 0) {
                val model = model as DefaultListModel
                val element1 = model.getElementAt(selectedIndex)
                val element2 = model.getElementAt(selectedIndex - 1)

                model.set(selectedIndex, element2)
                model.set(selectedIndex - 1, element1)

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
