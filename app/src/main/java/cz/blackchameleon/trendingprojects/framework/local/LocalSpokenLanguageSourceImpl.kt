package cz.blackchameleon.trendingprojects.framework.local

import android.content.Context
import cz.blackchameleon.data.local.LocalSpokenLanguageSource
import cz.blackchameleon.domain.SpokenLanguage
import cz.blackchameleon.trendingprojects.framework.database.Database
import cz.blackchameleon.trendingprojects.framework.database.dao.SpokenLanguageDao
import cz.blackchameleon.trendingprojects.framework.database.db.SpokenLanguageDb

/**
 * Local source implementation for getting spoken language object from DB
 * Implementation of [LocalSpokenLanguageSource]
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
class LocalSpokenLanguageSourceImpl(
    context: Context
) : LocalSpokenLanguageSource {
    private val database = Database.getInstance(context)
    private val spokenLanguageDao: SpokenLanguageDao?

    init {
        spokenLanguageDao = database?.spokenLanguageDao()
    }

    override suspend fun getSpokenLanguages(): List<SpokenLanguage>? =
        spokenLanguageDao?.getSpokenLanguages()?.map { it.toSpokenLanguage() }

    override suspend fun saveSpokenLanguage(spokenLanguage: SpokenLanguage) {
        spokenLanguage.run {
            spokenLanguageDao?.insert(
                SpokenLanguageDb(
                    urlParam ?: "", name
                )
            )
        }
    }

    override suspend fun clean() {
        spokenLanguageDao?.deleteAll()
    }
}