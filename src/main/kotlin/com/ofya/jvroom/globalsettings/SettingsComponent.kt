package com.ofya.jvroom.globalsettings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.fields.IntegerField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JSeparator


class SettingsComponent {
  private var mainPanel: JPanel
  private var switchToSelectedFile = JBCheckBox("Switch to selected file after editing JVroom files")
  private var reorderFilesAfterEdit = JBCheckBox("Reorder files after editing JVroom files (closes files not in list)")
  private var editFilesWindowWidth = IntegerField("Width", 0, Int.MAX_VALUE)
  private var editFilesWindowHeight = IntegerField("Height", 0, Int.MAX_VALUE)

  init {
    val separator = JSeparator()
    separator.toolTipText = "Edit files window settings"

    mainPanel = FormBuilder.createFormBuilder()
      .addComponent(switchToSelectedFile)
      .addComponent(reorderFilesAfterEdit)
      .addVerticalGap(10)
      .addLabeledComponent("Edit files window settings", JSeparator())
      .addLabeledComponent("Width", editFilesWindowWidth)
      .addLabeledComponent("Height", editFilesWindowHeight)
      .addComponentFillVertically(JPanel(), 0)
      .panel
  }

  fun getPanel(): JPanel {
    return mainPanel
  }

  fun getPreferredFocusedComponent(): JComponent {
    return switchToSelectedFile
  }

  fun getSwitchToSelectedFile(): Boolean {
    return switchToSelectedFile.isSelected
  }

  fun setSwitchToSelectedFile(newStatus: Boolean) {
    switchToSelectedFile.isSelected = newStatus
  }

  fun getReorderFilesAfterEdit(): Boolean {
    return reorderFilesAfterEdit.isSelected
  }

  fun setReorderFilesAfterEdit(newStatus: Boolean) {
    reorderFilesAfterEdit.isSelected = newStatus
  }

  fun getEditFilesWindowWidth(): Int {
    return editFilesWindowWidth.value
  }

  fun setEditFilesWindowWidth(newWidth: Int) {
    editFilesWindowWidth.value = newWidth
  }

  fun getEditFilesWindowHeight(): Int {
    return editFilesWindowHeight.value
  }

  fun setEditFilesWindowHeight(newHeight: Int) {
    editFilesWindowHeight.value = newHeight
  }
}