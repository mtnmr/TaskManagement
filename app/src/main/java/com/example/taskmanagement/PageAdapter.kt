package com.example.taskmanagement

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){

    private val todoFragment = TodoFragment()
    private val wipFragment = wipFragment()
    private val allFragment = allFragment()

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                todoFragment
            }
            1 -> {
                wipFragment
            }
            else ->  {
                allFragment
            }
        }
    }


    override fun getItemCount(): Int {
        return 3
    }
}