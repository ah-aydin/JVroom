package com.ofya.jpoon.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class SettingsComponent {
    private var mainPanel: JPanel
    private var closeFilesAfterOpenFile = JBCheckBox("Close the open files when switching between files")
    private var switchToSelectedFile = JBCheckBox("Switch to selected file after editing JPoon files")

    init {
        mainPanel = FormBuilder.createFormBuilder()
            .addComponent(closeFilesAfterOpenFile, 1)
            .addComponent(switchToSelectedFile, 2)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPanel(): JPanel {
        return mainPanel
    }

    fun getPreferredFocusedComponent(): JComponent {
        return closeFilesAfterOpenFile
    }

    fun getCloseFilesAfterOpenFile(): Boolean {
        return closeFilesAfterOpenFile.isSelected
    }

    fun setCloseFilesAfterOpenFile(newStatus: Boolean) {
        closeFilesAfterOpenFile.isSelected = newStatus
    }

    fun getSwitchToSelectedFile(): Boolean {
        return switchToSelectedFile.isSelected
    }

    fun setSwitchToSelectedFile(newStatus: Boolean) {
        switchToSelectedFile.isSelected = newStatus
    }
}