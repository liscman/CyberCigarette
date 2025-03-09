package com.example.cybercigarette.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cybercigarette.fragments.AbsorptionFragment
import com.example.cybercigarette.fragments.CoilFragment
import com.example.cybercigarette.fragments.DIYFragment
import com.example.cybercigarette.fragments.MixingFragment
import com.example.cybercigarette.fragments.TheoryFragment

class MainPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TheoryFragment()
            1 -> DIYFragment()
            2 -> MixingFragment()
            3 -> AbsorptionFragment()
            4 -> CoilFragment()
            else -> TheoryFragment()
        }
    }
}