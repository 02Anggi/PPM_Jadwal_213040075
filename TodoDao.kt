package id.ac.unpas.jadwal.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.jadwal.models.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun loadAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun load(id: String): LiveData<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: String): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Todo)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun delete(id: String)

    @Delete
    suspend fun delete(item: Todo)
}