package org.dev1.mapper

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.dev1.model.FileInfo


@Mapper
interface FileSystemManagerMapper {


    @Insert("INSERT INTO " +
            "fileinfo(id, filename, extension, size, modifiertime, physicspath, logicpath, filetype, filehash) " +
            "values (#{id},#{filename},#{extension},#{size},#{modifiertime},#{physicspath},#{logicpath},#{filetype},#{filehash})")
    fun insert(File:FileInfo):Int

    @Delete("DELETE from fileinfo WHERE id = #{id}")
    fun delete(File: FileInfo):Int

    @Select("SELECT * FROM fileinfo where id = #{id}")
    fun select(File: FileInfo):List<FileInfo>


    fun update(File: FileInfo):Int


}