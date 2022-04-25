package com.dkumar.groceryapp.activity

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.adapter.productAdapter
import com.dkumar.groceryapp.model.productsResponse
import com.dkumar.groceryapp.utility.Utility
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_products_list.*

class ProductsListActivity : AppCompatActivity() {
    lateinit var prodAdapter:productAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        initPage()
    }
    private fun initPage(){
        product_category_name_heading.text = intent.getStringExtra(Utility.SUB_CAT_NAME)
        getProducts()
        prodAdapter = productAdapter(this)
        product_recycler_view.adapter =prodAdapter
        product_recycler_view.layoutManager = LinearLayoutManager(this)

    }

    private fun getProducts() {
        val subCatId:Int = intent.getIntExtra(Utility.SUB_CAT_ID,0)
        var requestQue = Volley.newRequestQueue(this)
        var rq = StringRequest(
            Request.Method.GET,
            Utility.PRODUCT_URL+subCatId.toString(),
            {
                product_progress_bar.visibility = View.GONE
                var gson = Gson()
                var product = gson.fromJson(it,productsResponse::class.java)
                prodAdapter.setData(product.data)
            },
            {
                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
            }
        )
        requestQue.add(rq)
    }
}