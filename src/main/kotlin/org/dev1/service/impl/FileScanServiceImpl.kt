package org.dev1.service.impl

import org.dev1.mapper.FileInfoMapper
import org.dev1.model.FileInfoDb
import org.dev1.service.FileScanService
import org.dev1.util.SnowflakeIdGenerator
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path


@Service
class FileScanServiceImpl( private val mapper: FileInfoMapper): FileScanService {
    private val idGen = SnowflakeIdGenerator(1, 1)

    /**
     * 扫描文件存储到数据库
     */
    fun scanAndSave(root: Path) {
        val list = Files.walk(root)
            .filter { Files.isRegularFile(it) }
            .map { p ->
                FileInfoDb(
                    id = idGen.nextId(),
                    filepath = p.parent.toString(),
                    filename = p.fileName.toString(),
                    filesize = Files.size(p),
                    extension = p.fileName.toString()
                        .substringAfterLast('.', "")
                )
            }
            .toList()

        mapper.batchInsert(list)

    }
}