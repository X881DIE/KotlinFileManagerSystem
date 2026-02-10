package org.dev1.util

import java.nio.file.Files
import java.nio.file.Path

import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.name


/**
 * 文件元信息类
 * @param name :文件名
 * @param path : 文件位置
 * @param size :文件大小
 * @param extension 文件扩展名
 */
data class FileInfo(
    val name: String,
    val path:Path,
    val size:Long,
    val extension:String
)

/**
 * 扫描文件夹
 * @param dir:文件夹类
 */
fun scanDir(dir:Path):List<FileInfo>{
    require(Files.isDirectory(dir)){
        "Path is not a directory:$dir"
    }
    Files.list(dir).use { stream ->
        return stream
            .filter{it.isRegularFile()}
            .map { path->
                FileInfo(
                    name = path.name,
                    path = path,
                    size = Files.size(path),
                    extension = path.extension
                )
            }.toList()

    }
}

/**
 *
 * 树状遍历文件
 *
 */
fun treeDir(path: Path):List<FileInfo>{
    require(Files.exists(path)) {
        "Path does not exist: $path"
    }

    Files.walk(path).use { stream ->
        return stream
            .filter { Files.isRegularFile(it) }
            .map { p ->
                FileInfo(
                    name = p.fileName.toString(),
                    path = p,
                    size = Files.size(p),
                    extension = p.extension
                )
            }
            .toList()
    }

}