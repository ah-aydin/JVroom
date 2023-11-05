package com.ofya.jpoon.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent


class SettingsConfigurable : Configurable {

    private lateinit var settingsComponent: SettingsComponent
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "JPoon Settings"
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
        return settingsComponent.getSwitchToSelectedFile() != settings.switchToSelectedFile ||
                settingsComponent.getCloseFilesAfterOpenFile() != settings.closeFilesAfterOpenFile
    }

    override fun apply() {
        val settings: SettingsState = SettingsState.getInstance()
        settings.closeFilesAfterOpenFile = settingsComponent.getCloseFilesAfterOpenFile()
    }

    override fun reset() {
        val settings: SettingsState = SettingsState.getInstance()
        settingsComponent.setCloseFilesAfterOpenFile(settings.closeFilesAfterOpenFile)
        settingsComponent.setSwitchToSelectedFile(settings.switchToSelectedFile)
    }

    override fun disposeUIResources() {
    }
}