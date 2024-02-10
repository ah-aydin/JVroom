package com.ofya.jvroom.globalsettings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent


class SettingsConfigurable : Configurable {

  private lateinit var settingsComponent: SettingsComponent
  override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
    return "JVroom Settings"
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
        settingsComponent.getReorderFilesAfterEdit() != settings.reorderFilesAfterEdit ||
        settingsComponent.getEditFilesShowOnlyFileName() != settings.editFilesShowOnlyFileName ||
        settingsComponent.getEditFilesWindowWidth() != settings.editFilesWindowWidth ||
        settingsComponent.getEditFilesWindowHeight() != settings.editFilesWindowHeight
  }

  override fun apply() {
    val settings: SettingsState = SettingsState.getInstance()
    settings.switchToSelectedFile = settingsComponent.getSwitchToSelectedFile()
    settings.reorderFilesAfterEdit = settingsComponent.getReorderFilesAfterEdit()
    settings.editFilesShowOnlyFileName = settingsComponent.getEditFilesShowOnlyFileName()
    settings.editFilesWindowWidth = settingsComponent.getEditFilesWindowWidth()
    settings.editFilesWindowHeight = settingsComponent.getEditFilesWindowHeight()
  }

  override fun reset() {
    val settings: SettingsState = SettingsState.getInstance()
    settingsComponent.setSwitchToSelectedFile(settings.switchToSelectedFile)
    settingsComponent.setReorderFilesAfterEdit(settings.reorderFilesAfterEdit)
    settingsComponent.setEditFilesShowOnlyFileName(settings.editFilesShowOnlyFileName)
    settingsComponent.setEditFilesWindowWidth(settings.editFilesWindowWidth)
    settingsComponent.setEditFilesWindowHeight(settings.editFilesWindowHeight)
  }

  override fun disposeUIResources() {
  }
}