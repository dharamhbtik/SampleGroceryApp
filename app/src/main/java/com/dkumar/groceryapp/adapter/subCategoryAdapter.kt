package com.dkumar.groceryapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkumar.groceryapp.R
import com.dkumar.groceryapp.activity.ProductsListActivity
import com.dkumar.groceryapp.model.SubCategory
import com.dkumar.groceryapp.utility.Utility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sub_category_row_item.view.*

class subCategoryAdapter(var mcontext:Context):RecyclerView.Adapter<subCategoryAdapter.ViewHolder>() {
    var subCategoryList:ArrayList<SubCategory> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mcontext).inflate(R.layout.sub_category_row_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(subCategoryList[position])
    }
    fun setData(subCategories:ArrayList<SubCategory>){
        subCategoryList = subCategories
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
       return subCategoryList.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(subCat:SubCategory){
            itemView.tv_sub_category_name.text = subCat.subName
            Picasso.get().load(Utility.IMAGE_URL+subCat.subImage)
                .placeholder(R.drawable.noimage)
                .into(itemView.sub_category_image)
            itemView.setOnClickListener{
                val prIntent = Intent(mcontext,ProductsListActivity::class.java)
                    //intent.putExtra(Utility.CAT_ID,subCat.catId)
                    prIntent.putExtra(Utility.SUB_CAT_ID,subCat.subId)
                    prIntent.putExtra(Utility.SUB_CAT_NAME,subCat.subName)
                    mcontext.startActivity(prIntent)
            }
        }
    }

}