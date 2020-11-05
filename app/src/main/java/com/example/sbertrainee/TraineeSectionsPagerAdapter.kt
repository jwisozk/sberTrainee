package com.example.sbertrainee

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TraineeSectionsPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    lateinit var currentFragment: Fragment

    override fun createFragment(position: Int): Fragment {
        currentFragment = TraineePlaceholderFragment.newInstance(position + 1)
        return currentFragment
    }


    override fun getItemCount(): Int {
        return 1
    }
}