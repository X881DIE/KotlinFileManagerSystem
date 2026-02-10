package org.dev1.mapper

import org.apache.ibatis.annotations.Mapper
import org.dev1.model.FileInfoDb


@Mapper
interface FileInfoMapper {
    fun insert(fileInfo: FileInfoDb): Int


    fun batchInsert(list: List<FileInfoDb>): Int
}