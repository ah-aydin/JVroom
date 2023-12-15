package com.ofya.jvroom.globalsettings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil


@State(name = "com.ofya.jvroom.globalsettings.SettingsState", storages = [Storage("JVroomSettingsPlugin.xml")])
class SettingsState : PersistentStateComponent<SettingsState> {
  var switchToSelectedFile: Boolean = true
  var reorderFilesAfterEdit: Boolean = true
  var editFilesWindowWidth: Int = 1000
  var editFilesWindowHeight: Int = 300

  override fun getState(): SettingsState {
    return this
  }

  override fun loadState(state: SettingsState) {
    XmlSerializerUtil.copyBean(state, this)
  }

  companion object {
    fun getInstance(): SettingsState {
      return ApplicationManager.getApplication().getService(SettingsState::class.java)
    }
  }
}