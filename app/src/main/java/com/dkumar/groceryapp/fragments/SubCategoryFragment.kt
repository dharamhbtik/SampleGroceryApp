package com.dkumar.groceryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.adapter.productAdapter
import com.dkumar.groceryapp.model.Category
import com.dkumar.groceryapp.model.Product
import com.dkumar.groceryapp.model.productsResponse
import com.dkumar.groceryapp.utility.Utility
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sub_category.*
import kotlinx.android.synthetic.main.fragment_sub_category.view.*


class SubCategoryFragment : Fragment() {
    var mProductsList: ArrayList<Product> = ArrayList()
    lateinit var prodAdapter:productAdapter
    private var catId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            catId = it.getInt(Category.KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sub_category, container, false)
        getProducts(catId)
        prodAdapter = productAdapter(requireActivity().applicationContext)
        view.frag_product_recycler_view.adapter = prodAdapter
        view.frag_product_recycler_view.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
       // initPage(view)
        return view
    }

    private fun initPage(itemView: View?) {
       // getProducts(catId)
        prodAdapter = productAdapter(requireActivity().applicationContext)

       // itemView?.frag_product_recycler_view.adapter = prodAdapter
        //itemView?.frag_product_recycler_view.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
    }
    private fun getProducts(subCatId:Int) {
        var requestQue = Volley.newRequestQueue(requireActivity().applicationContext)
        var rq = StringRequest(
            Request.Method.GET,
            Utility.PRODUCT_URL+subCatId.toString(),
            {
                frag_product_progress_bar.visibility = View.GONE
                var gson = Gson()
                var product = gson.fromJson(it, productsResponse::class.java)
                prodAdapter.setData(product.data)
            },
            {
                Toast.makeText(requireActivity().applicationContext,it.message, Toast.LENGTH_LONG).show()
            }
        )
        requestQue.add(rq)
    }
    companion object {

        @JvmStatic
        fun newInstance(subCatId:Int) =
            SubCategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(Category.KEY,subCatId)
                }
            }
    }
}