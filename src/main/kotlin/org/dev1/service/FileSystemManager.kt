package org.dev1.service

import org.dev1.model.FileInfo
import org.dev1.util.FileOptionStatus

interface FileSystemManager {

    /**
     * 增加文件
     */
    fun addFile(file:FileInfo):FileOptionStatus

    /**
     * 删除文件
     */
    fun deleteFile(file: FileInfo):FileOptionStatus

    /**
     * 查询文件
     */
    fun selectFile(file: FileInfo):FileInfo

    /**
     * 修改文件
     */
    fun alterFile(file: FileInfo,alter:String):FileOptionStatus

    /**
     * 打开文件
     */
    fun openFile(file: FileInfo):ByteArray
}