package com.example.bottomnavigatiion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnavigatiion.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        checkAuth()
        binding.bottomNavigationView.setOnItemSelectedListener {
            onItemSelected(it)
        }
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun checkAuth() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            navController.navigate(R.id.signInFragment)
            binding.bottomNavigationView.visibility = View.GONE
        } else {
            navController.navigate(R.id.homeFragment)
            binding.bottomNavigationView.visibility = View.VISIBLE
        }
    }

    private fun onItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.miMessages -> {
                navController.navigate(R.id.messageFragment)
            }
            R.id.miSettings -> {
                navController.navigate(R.id.settingsFragment)
            }
            R.id.miProfile -> {
                navController.navigate(R.id.profileFragment)
            }
            else -> {
                navController.navigate(R.id.homeFragment)
            }
        }
        return true
    }
}