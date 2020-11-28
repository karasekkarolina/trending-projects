package cz.blackchameleon.trendingprojects

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

/**
 * Main activity in single activity architecture.
 * @see AppCompatActivity
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TrendingProjects)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_developer, R.id.navigation_repository
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        // Checks if internet connection is available and notifies user
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).registerNetworkCallback(
            NetworkRequest.Builder().build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onLost(network: Network) =
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.offline_error),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}