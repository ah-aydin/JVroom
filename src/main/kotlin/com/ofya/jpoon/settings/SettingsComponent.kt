package com.ofya.jpoon.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class SettingsComponent {
    private var myMainPanel: JPanel
    private var closeFilesAfterOpenFile = JBCheckBox("Close the open files when switching between files")

    init {
        myMainPanel = FormBuilder.createFormBuilder()
            .addComponent(closeFilesAfterOpenFile, 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPanel(): JPanel {
        return myMainPanel
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
}