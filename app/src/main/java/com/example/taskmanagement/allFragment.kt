package com.example.taskmanagement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.realm.Realm


class allFragment() : Fragment() {
    private lateinit var adapter:CustomRecyclerViewAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        realm = Realm.getDefaultInstance()

        val view = inflater.inflate(R.layout.fragment_all, container, false)
        // Inflate the layout for this fragment
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview3)
        layoutManager = LinearLayoutManager(view.context)
        myRecyclerView.layoutManager = layoutManager

        return view
    }

    override fun onStart() {
        super.onStart()
        val realmResults = realm.where(Todo::class.java)
            .findAll()
        val myRecyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerview3)
        adapter = CustomRecyclerViewAdapter(realmResults)
        myRecyclerView.adapter = this.adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}