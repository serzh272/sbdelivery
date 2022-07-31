package ru.skillbranch.sbdelivery.data.remote.api.response

data class DishDto(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val oldPrice: String,
    val price: Int,
    val rating: Double,
    val likes: Int,
    val category: String,
    val commentsCount: Int,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)