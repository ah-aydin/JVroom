package com.ofya.jpoon.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil


@State(name = "com.ahaydin.jpoon.settings.SettingsState", storages = [Storage("JPoonSettingsPlugin.xml")])
internal class SettingsState : PersistentStateComponent<SettingsState> {
    var userId = "John Q. Public"
    var ideaStatus = false

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