package com.tomberghuis.voicenotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import kotlinx.android.synthetic.main.navigation_activity.*

//import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
//import kotlinx.android.synthetic.main.navigation_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var voiceNotesViewModel: VoiceNotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)


        // will it blend
       // setSupportActionBar(toolbar as Toolbar?)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        val navController = host.navController
        setupActionBar(navController)

        // Get a new or existing ViewModel from the ViewModelProvider.
        voiceNotesViewModel = ViewModelProviders.of(this).get(VoiceNotesViewModel::class.java)

        // TODO can be removed
//        navController.addOnNavigatedListener { _, destination ->
//            val dest: String = try {
//                resources.getResourceName(destination.id)
//            } catch (e: Resources.NotFoundException) {
//                Integer.toString(destination.id)
//            }
//            Toast.makeText(this@MainActivity, "Navigated to $dest",
//                    Toast.LENGTH_SHORT).show()
//            Log.d("NavigationActivity", "Navigated to $dest")
//        }

    }

    private fun setupActionBar(navController: NavController) {
//        setupActionBarWithNavController(this,navController,null)
        setupActionBarWithNavController(this, navController)
    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(null,
//                Navigation.findNavController(this, R.id.my_nav_host_fragment))
//    }

}
