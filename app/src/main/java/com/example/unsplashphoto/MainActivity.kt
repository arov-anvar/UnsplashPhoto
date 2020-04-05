package com.example.unsplashphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.unsplashphoto.ui.collection.CurrentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CurrentAdapter.CurrentItemClickListener {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHost)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_picture -> {
                    navController.navigate(R.id.dailyPictureFragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_gallery -> {
                    navController.navigate(R.id.galleryFragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_search -> {
                    navController.navigate(R.id.searchFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }

            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun onCurrentItemSelected(id: String) {

    }
}
