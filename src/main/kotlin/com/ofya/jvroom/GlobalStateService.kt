package com.ofya.jvroom

import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
class GlobalStateService {
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
}