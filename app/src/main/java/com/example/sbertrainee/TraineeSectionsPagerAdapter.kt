package com.example.sbertrainee

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TraineeSectionsPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment =
        TraineePlaceholderFragment.newInstance(position + 1)

    override fun getItemCount(): Int {
        return 2
    }
}