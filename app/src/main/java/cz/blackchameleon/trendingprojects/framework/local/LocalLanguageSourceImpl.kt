package cz.blackchameleon.trendingprojects.framework.local

import android.content.Context
import cz.blackchameleon.data.local.LocalLanguageSource
import cz.blackchameleon.domain.Language
import cz.blackchameleon.trendingprojects.framework.database.Database
import cz.blackchameleon.trendingprojects.framework.database.dao.LanguageDao
import cz.blackchameleon.trendingprojects.framework.database.db.LanguageDb

/**
 * Local source implementation for getting language object from DB
 * Implementation of [LocalLanguageSource]
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
class LocalLanguageSourceImpl(
    context: Context
) : LocalLanguageSource {
    private val database = Database.getInstance(context)
    private val languageDao: LanguageDao?

    init {
        languageDao = database?.languageDao()
    }

    override suspend fun getLanguages(): List<Language>? =
        languageDao?.getLanguages()?.map { it.toLanguage() }

    override suspend fun saveLanguage(language: Language) {
        language.run {
            languageDao?.insert(
                LanguageDb(
                    urlParam, name
                )
            )
        }
    }

    override suspend fun clean() {
        languageDao?.deleteAll()
    }
}