package com.ofya.jpoon.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class SettingsComponent {
    private var myMainPanel: JPanel
    private var myUserNameText = JBTextField()
    private var myIdeaUserStatus = JBCheckBox("Do you use intelliJ")

    init {
        myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Enter user name: "), myUserNameText)
            .addComponent(myIdeaUserStatus, 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPanel(): JPanel {
        return myMainPanel
    }

    fun getPreferredFocusedComponent(): JComponent {
        return myUserNameText
    }

    fun getUserNameText(): String {
        return myUserNameText.text
    }

    fun setUserNameText(newText: String) {
        myUserNameText.text = newText
    }

    fun getIdeaUserStatus(): Boolean {
        return myIdeaUserStatus.isSelected
    }

    fun setIdeaUserStatus(newStatus: Boolean) {
        myIdeaUserStatus.isSelected = newStatus
    }
}