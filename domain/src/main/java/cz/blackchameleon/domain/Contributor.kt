package cz.blackchameleon.domain

/**
 * Representation of contributor entity
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
data class Contributor(
    val username: String,
    val href: String,
    val avatar: String
)