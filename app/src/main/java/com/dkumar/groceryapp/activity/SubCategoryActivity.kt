package com.dkumar.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.model.SubCategory
import com.dkumar.groceryapp.model.subCategoryResponse
import com.dkumar.groceryapp.utility.Utility
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main_tab.*
import com.dkumar.groceryapp.adapter.SubCategoryFragmentAdapter



class SubCategoryActivity : AppCompatActivity() {
   // lateinit var prodAdapter: productAdapter
   // lateinit var subCatAdapter: subCategoryAdapter
    var subCategoryList: ArrayList<SubCategory> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        init()
    }
    private fun init(){
        getSubCategories()
       // setupTabLayout()
//        subCatAdapter = subCategoryAdapter(this)
//        sub_cate_recycler_view.adapter = subCatAdapter
//        sub_cate_recycler_view.layoutManager = LinearLayoutManager(this)
//        sub_cate_recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
    }

    private fun setupTabLayout() {
        var subCatFragAdapter = SubCategoryFragmentAdapter(supportFragmentManager)
        for (category in subCategoryList){
            subCatFragAdapter.addFragment(category)
        }
        view_pager.adapter = subCatFragAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    private fun getSubCategories() {
            var requestQueue = Volley.newRequestQueue(this)
            var rq = StringRequest(
                Request.Method.GET,
                Utility.SUB_CATEGORY_URL,
                { it ->
                    //sub_cat_progress_bar.visibility = View.GONE
                    var gson = Gson()
                    var catResponse = gson.fromJson(it, subCategoryResponse::class.java)
                    val catId:Int = intent.getIntExtra(Utility.CAT_ID,0)
                    val filteredSubCats = catResponse.data.filter {
                        it.catId == catId
                    }
                    subCategoryList = filteredSubCats as ArrayList<SubCategory>
                    setupTabLayout();
                   // subCatAdapter.setData(filteredSubCats as ArrayList<SubCategory>)
                },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                }
            )
            requestQueue.add(rq)
    }

}