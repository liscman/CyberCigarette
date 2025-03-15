package com.example.cybercigarette

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.cybercigarette.adapters.MainPagerAdapter
import com.example.cybercigarette.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: MainPagerAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPagerAndNav()

    }

    /** Setup MainViewPager and MainBottomNavigation by bottom_nav_menu */
    private fun setupViewPagerAndNav(){
        val bottomNavigationView = binding.mainBottomNav
        val viewPager = binding.mainViewpager

        pagerAdapter = MainPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        bottomNavigationView.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.bottom_nav_theory -> viewPager.currentItem = 0
                    R.id.bottom_nav_diy -> viewPager.currentItem = 1
                    R.id.bottom_nav_mixing -> viewPager.currentItem = 2
                    R.id.bottom_nav_absorption -> viewPager.currentItem = 3
                    R.id.bottom_nav_coil -> viewPager.currentItem = 4
                }
                return true
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> bottomNavigationView.selectedItemId = R.id.bottom_nav_theory
                    1 -> bottomNavigationView.selectedItemId = R.id.bottom_nav_diy
                    2 -> bottomNavigationView.selectedItemId = R.id.bottom_nav_mixing
                    3 -> bottomNavigationView.selectedItemId = R.id.bottom_nav_absorption
                    4 -> bottomNavigationView.selectedItemId = R.id.bottom_nav_coil
                }
            }
        })

        bottomNavigationView.selectedItemId = R.id.bottom_nav_mixing
        viewPager.currentItem = 2
    }
}