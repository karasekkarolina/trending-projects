package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import cz.blackchameleon.domain.DeveloperRepo

/**
 * Developer repo type converter for representation in DB
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
class DeveloperRepoTypeConverter {
    @TypeConverter
    fun stringToDeveloperRepo(string: String): DeveloperRepo? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(DeveloperRepo::class.java)

        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun developerRepoToString(developerRepo: DeveloperRepo): String {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(DeveloperRepo::class.java)

        return jsonAdapter.toJson(developerRepo)
    }
}