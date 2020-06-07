package com.eshop.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eshop.R
import com.eshop.ui.maps.MapsActivity
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val categoryGridView: GridView = root.findViewById(R.id.category_grid)
        homeViewModel.listCategory.observe(viewLifecycleOwner, Observer {
            val gridAdapter = activity?.let { it1 -> CategoryGridAdapter(it1, it) }
            categoryGridView.adapter = gridAdapter
        })

        root.location_fab.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            intent.putExtra("ClassType", "NearBy")
            startActivity(intent)
        }
        root.add_shop_fab.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            intent.putExtra("ClassType", "AddShop")
            startActivity(intent)
        }
        return root
    }
}