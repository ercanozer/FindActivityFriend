package com.example.findactivityfriend.ui.mainUiActions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.findactivityfriend.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        val bottomNavigationView : BottomNavigationView = findViewById<BottomNavigationView>(R.id.bttm_nav)

        val NavHost :NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.managerFragment) as NavHostFragment;
        NavigationUI.setupWithNavController(bottomNavigationView,NavHost.navController)
    }
}