package com.example.samachar.repository.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.samachar.model.Source

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromSourceName(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}