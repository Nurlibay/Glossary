package uz.texnopos.glossary.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import uz.texnopos.glossary.data.entity.Glossary

@Dao
interface GlossaryDao {

    @Query("SELECT * FROM glossary ORDER BY id ASC")
    suspend fun readAllData(): List<Glossary>

    // @Query("SELECT * FROM dictionary_app WHERE eng_name LIKE '%' || :engName || '%'")
    @Query("SELECT * FROM glossary WHERE term LIKE  :term")
    fun getWordByName(term: String): List<Glossary>

    @Query("SELECT * FROM glossary WHERE term LIKE :searchQuery")
    suspend fun searchDatabase(searchQuery: String): List<Glossary>

    @Query("SELECT * FROM glossary WHERE id=:id")
    suspend fun getWordMeans(id: Int): Glossary

    @Update
    suspend fun update(glossary: Glossary)

    @Query("SELECT * FROM glossary WHERE status = 1")
    suspend fun getFavourites(): List<Glossary>

}