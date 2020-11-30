package cz.blackchameleon.domain

import java.io.Serializable

/**
 * Representation of filter class
 * Provides two type aliases for better filters specification
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
data class Filter(
    val urlParam: String?,
    val name: String
) : Serializable

typealias Language = Filter
typealias SpokenLanguage = Filter
