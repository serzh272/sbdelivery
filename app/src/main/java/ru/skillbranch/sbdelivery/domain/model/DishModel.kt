package ru.skillbranch.sbdelivery.domain.model

data class DishModel(
    val id: Long,
    val name: String,
    val image: String,
    val price: Int,
    val isFavorite: Boolean = false,
    val category: String,
    val promotion: Boolean,
    val apiId: Long? = null
)