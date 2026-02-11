package org.dev1.model

import java.math.BigInteger
import java.nio.file.Path
import java.time.LocalDateTime
import java.util.Date



class FileInfo(
    val id:Long,
    val filename:String,
    val extension:String,
    val size:Long,
    val modifiertime:LocalDateTime,
    val physicspath:String,
    val logicpath:String,
    val filetype:String,
    val filehash:String
) {
}