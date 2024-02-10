package com.ofya.jvroom.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.ofya.jvroom.globalsettings.SettingsState
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.DefaultListModel
import javax.swing.JComponent

class EditFilePathsPopupDialogUI(filePaths: List<String>, projectBasePath: String) : DialogWrapper(true) {
  private val doubleKeyPressMaxDelayInMilliseconds = 300
  private var previousDeleteKeyPress: Long = 0

  private val filePathLabels: JBList<FilePathLabel>

  init {
    val jLabels = mutableListOf<FilePathLabel>()
    filePaths.iterator().forEach {
      jLabels.add(FilePathLabel(it, projectBasePath))
    }
    this.filePathLabels = JBList<FilePathLabel>(jLabels)
    this.filePathLabels.selectedIndex = 0
    this.filePathLabels.addKeyListener(
      object : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
          processKeyEvent(e)
        }
      }
    )
    init()
    title = "JVroom Files"
  }

  override fun createCenterPanel(): JComponent {
    val settingsState = SettingsState.getInstance()

    val scrollPane = JBScrollPane(filePathLabels)
    scrollPane.preferredSize = Dimension(settingsState.editFilesWindowWidth, settingsState.editFilesWindowHeight)

    filePathLabels.requestFocus()
    return scrollPane
  }

  override fun getPreferredFocusedComponent(): JComponent {
    return filePathLabels
  }

  fun processKeyEvent(e: KeyEvent) {
    if (e.id != KeyEvent.KEY_PRESSED) {
      return
    }

    if (e.isShiftDown) {
      if (e.keyCode == KeyEvent.VK_J && filePathLabels.selectedIndex + 1 < filePathLabels.model.size) {
        val nextIndex = filePathLabels.selectedIndex + 1
        val model = filePathLabels.model as DefaultListModel
        val selectedElement = model.getElementAt(filePathLabels.selectedIndex)
        val nextElement = model.getElementAt(nextIndex)

        model.set(filePathLabels.selectedIndex, nextElement)
        model.set(nextIndex, selectedElement)

        filePathLabels.selectedIndex++
      } else if (e.keyCode == KeyEvent.VK_K && filePathLabels.selectedIndex > 0) {
        val previousIndex = filePathLabels.selectedIndex - 1
        val model = filePathLabels.model as DefaultListModel
        val selectedElement = model.getElementAt(filePathLabels.selectedIndex)
        val previousElement = model.getElementAt(previousIndex)

        model.set(filePathLabels.selectedIndex, previousElement)
        model.set(previousIndex, selectedElement)

        filePathLabels.selectedIndex--
      } else if (e.keyCode == KeyEvent.VK_D) {
        removeFileAtCursor()
      }
    } else {
      if (e.keyCode == KeyEvent.VK_J) {
        filePathLabels.selectedIndex++
      }
      if (e.keyCode == KeyEvent.VK_K) {
        filePathLabels.selectedIndex--
      }
      if (e.keyCode == KeyEvent.VK_D) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - previousDeleteKeyPress < doubleKeyPressMaxDelayInMilliseconds) {
          previousDeleteKeyPress = 0
          removeFileAtCursor()
        } else {
          previousDeleteKeyPress = currentTime
        }
      }
      if (e.keyCode == KeyEvent.VK_ENTER) {
        doOKAction()
      }
      if (e.keyCode == KeyEvent.VK_ESCAPE) {
        doCancelAction()
      }
    }
  }

  private fun removeFileAtCursor() {
    val model = filePathLabels.model as DefaultListModel
    val selectedIndex = filePathLabels.selectedIndex
    model.remove(filePathLabels.selectedIndex)
    filePathLabels.selectedIndex = if (selectedIndex >= model.size) {
      model.size - 1
    } else {
      selectedIndex
    }
  }

  fun isEmpty(): Boolean {
    return filePathLabels.model.size == 0
  }

  fun getFilePaths(): List<String> {
    val filePaths: MutableList<String> = mutableListOf()
    for (index in 0 until this.filePathLabels.model.size) {
      filePaths.add(this.filePathLabels.model.getElementAt(index).filePath)
    }
    return filePaths
  }

  fun getSelectedIndex(): Int {
    return filePathLabels.selectedIndex
  }

  private class FilePathLabel(filePath: String, projectBasePath: String) {

    val filePath: String = filePath
    val projectBasePath: String = projectBasePath

    override fun toString(): String {
      return this.filePath
    }
  }
}