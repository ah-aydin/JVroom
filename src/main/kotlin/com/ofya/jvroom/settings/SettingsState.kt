package com.ofya.jvroom.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil


@State(name = "com.ofya.jvroom.settings.SettingsState", storages = [Storage("JVroomSettingsPlugin.xml")])
internal class SettingsState : PersistentStateComponent<SettingsState> {
    var switchToSelectedFile: Boolean = true
    var reorderFilesAfterEdit: Boolean = true

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