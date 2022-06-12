package uz.texnopos.glossary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "glossary")
data class Glossary(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "term")
    val term: String,
    @ColumnInfo(name = "russian_mean")
    val russian_mean: String,
    @ColumnInfo(name = "eng_mean")
    val eng_mean: String,
    @ColumnInfo(name = "qq_mean")
    val qq_mean: String,
    @ColumnInfo(name = "status")
    var status: Int
)
