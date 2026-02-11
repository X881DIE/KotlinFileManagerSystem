package org.dev1.service

import java.nio.file.Path

interface FileScanService {

    fun scanAndSave(root: Path)
}