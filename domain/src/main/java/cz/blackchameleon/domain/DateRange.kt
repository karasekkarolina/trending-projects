package cz.blackchameleon.domain

/**
 * Representation of date filter used for [Repository] objects filtering
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
enum class DateRange(val dateRange: String) {
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly")
}