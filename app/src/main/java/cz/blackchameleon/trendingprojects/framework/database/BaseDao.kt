package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Base data access object with create, update, delete operations for DB
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}