package cz.blackchameleon.trendingprojects.extensions

import androidx.appcompat.widget.SearchView
import java.util.*

/**
 * @author Karolina Klepackova on 30.11.2020.
 */
fun SearchView.afterTextChangedDelayed(delay: Long = 500, afterTextChanged: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        private var timer: Timer? = null

        override fun onQueryTextChange(text: String?): Boolean {
            timer?.cancel()
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    post {
                        afterTextChanged.invoke(text.toString())
                    }
                }
            }, delay)
            return true
        }

        override fun onQueryTextSubmit(text: String?): Boolean {
            afterTextChanged(text ?: return false)
            return false
        }
    })
}