package cz.blackchameleon.domain

/**
 * Representation of developer repo entity
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
data class DeveloperRepo(
    val name: String,
    val url: String,
    val description: String
)