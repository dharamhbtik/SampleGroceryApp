package com.dkumar.groceryapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.activity.SubCategoryActivity
import com.dkumar.groceryapp.model.Category
import com.dkumar.groceryapp.utility.Utility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_row_item.view.*
import okhttp3.internal.Util

class CategoryAdapter(var mContext: Context): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var mCategoryList:ArrayList<Category> = ArrayList()
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(category:Category){
            itemView.tv_category_name.text = category.catName
            Picasso.get().load(Utility.IMAGE_URL+category.catImage)
                .placeholder(R.drawable.noimage)
                .into(itemView.category_image)
            itemView.setOnClickListener { navigateToSubCategory(category.catId) }
        }
        private fun navigateToSubCategory(catId:Int){
            val intent = Intent(mContext,SubCategoryActivity::class.java)
                intent.putExtra(Utility.CAT_ID,catId)
                mContext.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.category_row_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCategoryList[position])
    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }
    fun setData(categories:ArrayList<Category>){
        mCategoryList = categories
        notifyDataSetChanged()
    }
}