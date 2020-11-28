package cz.blackchameleon.data

/**
 * Class representing result of IO operation especially for DB and API data gathering
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
sealed class Result <T>{
    abstract val isSuccess: Boolean

    // Used for success scenario
    data class Success <T> (
        val data: T
    ): Result<T>() {
        override val isSuccess: Boolean
            get() = true
    }
    // Used for failure scenario
    data class Error <T>(
        val error: String? = null
    ): Result<T>() {
        override val isSuccess: Boolean
            get() = false
    }
}