package com.example.newsapp.local.data.util

import androidx.room.TypeConverter
import com.example.newsapp.common.data.remote.dto.Source

class Converter {
    @TypeConverter
    fun fromSource(source: Source):String{
        return source.id + "~" + source.name
    }

    @TypeConverter
    fun toSource(value: String):Source{
        return if (value.contains('~')){
            val source = value.split('~')
            Source(source[0], source[1])
        }else{
            Source(value, value)
        }
    }
}