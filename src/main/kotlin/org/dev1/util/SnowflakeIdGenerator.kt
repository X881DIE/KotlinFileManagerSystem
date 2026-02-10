package org.dev1.util

/**
 * <h2>雪花算法生成主键</h2>
 */
class SnowflakeIdGenerator(
    private val workerId: Long,
    private val datacenterId: Long
) {
    private val epoch = 1700000000000L

    private val workerIdBits = 5L
    private val datacenterIdBits = 5L
    private val sequenceBits = 12L

    private val maxWorkerId = -1L xor (-1L shl workerIdBits.toInt())
    private val maxDatacenterId = -1L xor (-1L shl datacenterIdBits.toInt())

    private val workerIdShift = sequenceBits
    private val datacenterIdShift = sequenceBits + workerIdBits
    private val timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits
    private val sequenceMask = -1L xor (-1L shl sequenceBits.toInt())

    private var lastTimestamp = -1L
    private var sequence = 0L

    init {
        require(workerId <= maxWorkerId)
        require(datacenterId <= maxDatacenterId)
    }

    @Synchronized
    fun nextId(): Long {
        var timestamp = System.currentTimeMillis()

        if (timestamp < lastTimestamp) {
            error("Clock moved backwards")
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) and sequenceMask
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp)
            }
        } else {
            sequence = 0L
        }

        lastTimestamp = timestamp

        return ((timestamp - epoch) shl timestampLeftShift.toInt()) or
                (datacenterId shl datacenterIdShift.toInt()) or
                (workerId shl workerIdShift.toInt()) or
                sequence
    }

    private fun tilNextMillis(lastTimestamp: Long): Long {
        var ts = System.currentTimeMillis()
        while (ts <= lastTimestamp) {
            ts = System.currentTimeMillis()
        }
        return ts
    }
}
