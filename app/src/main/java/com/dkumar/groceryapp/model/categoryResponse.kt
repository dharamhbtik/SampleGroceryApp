package com.dkumar.groceryapp.model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class categoryResponse(
    val count: Int,
    val data: ArrayList<Category>,
    val error: Boolean
)

data class Category(
    val __v: Int,
    val _id: String,
    val catDescription: String,
    val catId: Int,
    val catImage: String,
    val catName: String,
    val position: Int,
    val slug: String,
    val status: Boolean
): Serializable {
    companion object{
        const val KEY = "catId"
    }
}