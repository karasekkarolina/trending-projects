package cz.blackchameleon.domain

/**
 * Representation of developer entity
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
data class Developer(
    val username: String,
    val name: String,
    val url: String,
    val sponsorUrl: String,
    val avatar: String,
    val repo: DeveloperRepo
)