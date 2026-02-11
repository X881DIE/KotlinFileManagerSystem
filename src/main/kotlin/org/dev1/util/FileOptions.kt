package org.dev1.util

import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime

import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.name
import kotlin.streams.toList


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
){
    override fun toString(): String {
        return "$path,$size \n"
    }

}

/**
 * 统计文件的后缀名
 */
fun FileTypesCount(files: List<FileInfo>): Map<String,Int> {
    return files.groupingBy { it.extension }.eachCount()
}


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

val TypeMap:HashMap<String,String> = HashMap(100)

fun FileType(x: String): String {
    TypeMap["dll"] = "mp4"
    TypeMap["mp4"] = "MP4视频"
    TypeMap["exe"] = "windows可执行文件"
    TypeMap["txt"] = "TEXT文本文件"
    TypeMap["pdf"] = "便携式文档PDF"
    TypeMap["json"] = "java script对象文件"

     return if(TypeMap[x] == null) x else TypeMap[x].toString()
}
fun treePath(path: Path):List<org.dev1.model.FileInfo>{
    require(Files.exists(path)){
        "Path does not exist:$path"
    }
    val SnowId = SnowflakeIdGenerator(3,1)
    Files.walk((path)).use { stream ->
        return stream
            .filter{Files.isRegularFile(it)}
            .map { p->
                org.dev1.model.FileInfo(
                    id = SnowId.nextId(),
                    filename = p.fileName.toString(),
                    extension = p.extension,
                    size = Files.size(p),
                    modifiertime = LocalDateTime.now(),
                    physicspath = p.toString(),
                    logicpath = p.toString(),
                    filetype = FileType(p.extension),
                    filehash = p.hashCode().toString()
                )

             }.toList()
    }
}