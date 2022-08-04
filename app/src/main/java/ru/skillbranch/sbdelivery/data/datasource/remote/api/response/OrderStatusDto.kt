package ru.skillbranch.sbdelivery.data.datasource.remote.api.response

data class OrderStatusDto(
    val id: String,
    val name: String,
    val cancelable: Boolean,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)