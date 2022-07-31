package ru.skillbranch.sbdelivery.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.skillbranch.sbdelivery.data.remote.api.response.CategoryDto
import ru.skillbranch.sbdelivery.data.remote.api.response.DishDto
import ru.skillbranch.sbdelivery.data.remote.api.response.OrderStatusDto

interface OrderApi {

    @GET("categories")
    suspend fun getCategories(
        @Query("offset") offset:Int? = null,
        @Query("limit") limit:Int? = null,
    ): Response<List<CategoryDto>>

    @GET("dishes")
    suspend fun getDishes(
        @Query("offset") offset:Int? = null,
        @Query("limit") limit:Int? = null,
    ): Response<List<DishDto>>

    @GET("main/recommend")
    suspend fun getRecommendedDishesIds(): Response<List<String>>

    @GET("orders/statuses")
    suspend fun getOrderStatuses(): Response<List<OrderStatusDto>>
}