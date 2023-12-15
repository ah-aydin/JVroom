package com.ofya.jvroom

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@Service(Service.Level.PROJECT)
@State(
  name = "com.ofya.jvroom.ProjectStateService", storages = [Storage(
    "\$PROJECT_CONFIG_DIR\$/JVroomSettingsPlugin.xml"
  )]
)
class ProjectStateService : PersistentStateComponent<ProjectStateService> {
  private var filePaths: MutableList<String> = mutableListOf()

  fun addFilePath(filePath: String) {
    if (filePaths.contains(filePath)) {
      return
    }
    filePaths.add(filePath)
  }

  fun getFilePathAt(index: Int): String? {
    if (index >= filePaths.size) {
      return null
    }
    return filePaths[index]
  }

  fun setFilePaths(filePaths: List<String>) {
    this.filePaths = filePaths.toMutableList()
  }

  fun getFilePaths(): MutableList<String> {
    return filePaths
  }

  fun getFileCount(): Int {
    return filePaths.size
  }

  override fun getState(): ProjectStateService {
    return this
  }

  override fun loadState(state: ProjectStateService) {
    XmlSerializerUtil.copyBean(state, this)
  }
}