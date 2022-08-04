package ru.skillbranch.sbdelivery.presentation.navigation

import androidx.annotation.StringRes
import ru.skillbranch.sbdelivery.R

sealed class RootNavigation(val route: String) {
    object Main : RootNavigation("main")
    object Login : RootNavigation("login")
}
sealed class MainNavigation(val route: String, @StringRes val resourceId: Int? = null) {
    object Main : MainNavigation("main", R.string.main)
    object Login : MainNavigation("login")
}