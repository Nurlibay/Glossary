package uz.texnopos.glossary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.texnopos.glossary.data.dao.GlossaryDao
import uz.texnopos.glossary.data.entity.Glossary

@Database(entities = [Glossary::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun glossaryDao(): GlossaryDao
}