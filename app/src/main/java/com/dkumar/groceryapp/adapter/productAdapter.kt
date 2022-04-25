package com.dkumar.groceryapp.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.model.Product
import com.dkumar.groceryapp.utility.Utility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row_item.*
import kotlinx.android.synthetic.main.product_row_item.view.*

class productAdapter(var mContext: Context):RecyclerView.Adapter<productAdapter.ViewHolder>() {
    var productsList:ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.product_row_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(productsList[position])
    }

    override fun getItemCount(): Int {
       return productsList.size
    }
    fun setData(products:ArrayList<Product>){
        productsList = products
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(product:Product){
            itemView.tv_product_name.text = product.productName
            itemView.tv_product_quantity.text ="Quantit : "+ product.unit.toString()
            itemView.tv_product_mrp.text = "MRP: ₹${product.mrp}"
            itemView.tv_product_price.text = "Price: ₹${product.price}"
            itemView.tv_product_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            Picasso
                .get()
                .load(Utility.IMAGE_URL+product.image)
                .placeholder(R.drawable.noimage)
                .into(itemView.iv_product)
        }
    }

}