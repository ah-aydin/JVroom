package com.ahaydin.jpoon.state

import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
class GlobalStateService {
    private var filesList: MutableList<String> = mutableListOf()

    fun addFile(file: String) {
        if (filesList.contains(file)) {
            return
        }
        filesList.add(file)
    }

    fun getFileAt(index: Int): String? {
        if (index >= filesList.size) {
            return null
        }
        return filesList[index]
    }

    fun setFiles(files: List<String>) {
        this.filesList = files.toMutableList()
    }

    fun getFiles(): MutableList<String> {
        return filesList
    }
}