package com.ofya.jpoon.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent


class SettingsConfigurable : Configurable {

    private lateinit var settingsComponent: SettingsComponent
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "JPoon Settings Display Name"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return settingsComponent.getPreferredFocusedComponent()
    }

    override fun createComponent(): JComponent {
        settingsComponent = SettingsComponent()
        return settingsComponent.getPanel()
    }

    override fun isModified(): Boolean {
        val settings: SettingsState = SettingsState.getInstance()
        var modified: Boolean = settingsComponent.getUserNameText() != settings.userId
        modified = modified or (settingsComponent.getIdeaUserStatus() != settings.ideaStatus)
        return modified
    }

    override fun apply() {
        val settings: SettingsState = SettingsState.getInstance()
        settings.userId = settingsComponent.getUserNameText()
        settings.ideaStatus = settingsComponent.getIdeaUserStatus()
    }

    override fun reset() {
        val settings: SettingsState = SettingsState.getInstance()
        settingsComponent.setUserNameText(settings.userId)
        settingsComponent.setIdeaUserStatus(settings.ideaStatus)
    }

    override fun disposeUIResources() {
//        settingsComponent = null
    }
}