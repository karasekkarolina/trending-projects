package cz.blackchameleon.domain

import java.io.Serializable

/**
 * Representation of repository entity
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
data class Repository(
    val author: String,
    val name: String,
    val avatar: String,
    val url: String,
    val description: String,
    val language: String,
    val languageColor: String,
    val stars: String,
    val forks: String,
    val currentPeriodStars: String,
    var builtBy: List<Contributor> = emptyList()
): Serializable