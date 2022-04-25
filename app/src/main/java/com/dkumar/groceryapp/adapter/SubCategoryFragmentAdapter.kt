package com.dkumar.groceryapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dkumar.groceryapp.fragments.SubCategoryFragment
import com.dkumar.groceryapp.model.SubCategory

class SubCategoryFragmentAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    var mFragment: ArrayList<Fragment> = ArrayList()
    var mTitle: ArrayList<String> = ArrayList()
    override fun getCount(): Int {
        return mFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }

    fun addFragment(subCat: SubCategory){
        mFragment.add(SubCategoryFragment.newInstance(subCat.subId))
        mTitle.add(subCat.subName)
    }
}