package ru.skillbranch.sbdelivery.data.datasource.remote.api.response

data class CategoryDto(
    val categoryId: String,
    val name: String,
    val order: Int,
    val icon: String?,
    val parent: String?,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)