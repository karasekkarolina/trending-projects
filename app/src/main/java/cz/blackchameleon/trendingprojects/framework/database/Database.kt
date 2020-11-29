package cz.blackchameleon.trendingprojects.framework.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.blackchameleon.trendingprojects.framework.database.dao.ContributorDao
import cz.blackchameleon.trendingprojects.framework.database.dao.DeveloperDao
import cz.blackchameleon.trendingprojects.framework.database.dao.RepositoryDao
import cz.blackchameleon.trendingprojects.framework.database.db.ContributorDb
import cz.blackchameleon.trendingprojects.framework.database.db.DeveloperDb
import cz.blackchameleon.trendingprojects.framework.database.db.RepositoryDb

/**
 * Room database object with 2 given entities [DeveloperDao], [RepositoryDao]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@androidx.room.Database(
    entities = [
        DeveloperDb::class,
        RepositoryDb::class,
        ContributorDb::class
    ], exportSchema = false, version = 2
)
@TypeConverters(DeveloperRepoTypeConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    abstract fun repositoryDao(): RepositoryDao

    abstract fun contributorDao(): ContributorDao

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