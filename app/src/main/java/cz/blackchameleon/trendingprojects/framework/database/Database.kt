package cz.blackchameleon.trendingprojects.framework.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database object with 2 given entities [DeveloperDao], [RepositoryDao]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@androidx.room.Database(
    entities = [
        DeveloperDb::class,
        RepositoryDb::class
    ], exportSchema = false, version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    abstract fun repositoryDao(): RepositoryDao

    companion object {
        private const val DB_NAME = "database"
        var instance: Database? = null

        fun getInstance(context: Context): Database? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}