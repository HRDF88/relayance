package com.kirabium.relayance.data.database

import androidx.room.TypeConverter
import java.util.Date

/**
 * A utility class for converting complex data types to and from formats
 * that Room can store in the database.
 */
class Converters {

    /**
     * A utility class for converting complex data types to and from formats
     * that Room can store in the database.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Converts a Date object to a timestamp (Long).
     *
     * @param date The Date object to convert, or null if not available.
     * @return A timestamp representing the given Date, or null if the input is null.
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}