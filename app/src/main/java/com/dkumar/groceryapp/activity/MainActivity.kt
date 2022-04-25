package com.dkumar.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.adapter.CategoryAdapter
import com.dkumar.groceryapp.model.categoryResponse
import com.dkumar.groceryapp.utility.Utility
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var catAdapter: CategoryAdapter
    private lateinit var drawerLayout : DrawerLayout
    private  lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        getCategories()
        catAdapter = CategoryAdapter(this)

        recycler_view.adapter = catAdapter
        recycler_view.layoutManager = GridLayoutManager(this,2)
        setupDrawer()
    }

    private fun setupDrawer() {
        setupToolbar()
       // drawerLayout = drawer_layout
       // navView = nav_view
        var toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun setupToolbar() {
        var mytoolBar = toolbar
        mytoolBar.title="Home"
        setSupportActionBar(mytoolBar)
    }

    private fun getCategories() {
        var requestQueue = Volley.newRequestQueue(this)
        var rq = StringRequest(
            Request.Method.GET,
            Utility.CATEGORY_URL,
            {
                progress_bar.visibility = View.GONE
                var gson = Gson()
                var catResponse = gson.fromJson(it,categoryResponse::class.java)
                catAdapter.setData(catResponse.data)
            },
            {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(rq)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           // R.id.my_account-> Toast.makeText(this,"My Account clicked",Toast.LENGTH_LONG).show()
           // R.id.my_order-> Toast.makeText(this,"My Order clicked",Toast.LENGTH_LONG).show()
        }
        return true
    }
}