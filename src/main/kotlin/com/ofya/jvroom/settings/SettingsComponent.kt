package com.ofya.jvroom.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class SettingsComponent {
    private var mainPanel: JPanel
    private var switchToSelectedFile = JBCheckBox("Switch to selected file after editing JVroom files")
    private var reorderFilesAfterEdit = JBCheckBox("Reorder files after editing JVroom files (closes files not in list)")

    init {
        mainPanel = FormBuilder.createFormBuilder()
            .addComponent(switchToSelectedFile, 2)
            .addComponent(reorderFilesAfterEdit, 3)
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
}