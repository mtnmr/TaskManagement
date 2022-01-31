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
import kotlin.String as String1


class TodoFragment() : Fragment() {
    private lateinit var adapter:CustomRecyclerViewAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        realm = Realm.getDefaultInstance()

        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        // Inflate the layout for this fragment
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview1)
        layoutManager = LinearLayoutManager(view.context)
        myRecyclerView.layoutManager = layoutManager

        return view
    }

    override fun onStart() {
        super.onStart()
        val realmResults = realm.where(Todo::class.java)
            .equalTo("status", "TODO")
            .findAll()

        val myRecyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerview1)
        adapter = CustomRecyclerViewAdapter(realmResults)
        myRecyclerView.adapter = this.adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment TodoFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TodoFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
