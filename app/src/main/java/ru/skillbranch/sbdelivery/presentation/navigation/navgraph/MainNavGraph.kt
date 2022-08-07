package ru.skillbranch.sbdelivery.presentation.navigation.navgraph

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.presentation.home.HomeViewModel
import ru.skillbranch.sbdelivery.presentation.root.*

@Composable
fun MainNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController,
        startDestination = MainNavigationDestination.Main.route,
        modifier = modifier
    ) {
        composable(MainNavigationDestination.Main.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController, viewModel)
        }
        composable(MainNavigationDestination.Menu.route) {
            MenuScreen(navController)
        }
        composable(MainNavigationDestination.Favorites.route) {
            FavoritesScreen(navController)
        }
        composable(MainNavigationDestination.Cart.route) {
            CartScreen(navController)
        }
        composable(MainNavigationDestination.Profile.route) {
            ProfileScreen(navController)
        }
        composable(MainNavigationDestination.Orders.route) {
            OrdersScreen(navController)
        }
        composable(MainNavigationDestination.Notifications.route) {
            NotificationsScreen(navController)
        }
        composable(MainNavigationDestination.About.route) {
            AboutScreen(navController)
        }
    }
}

sealed class MainNavigationDestination(
    val route: String,
    @DrawableRes val iconRes: Int,
    @StringRes val nameRes: Int
) {
    object Main : MainNavigationDestination("main", R.drawable.ic_home_24, R.string.main)
    object Menu : MainNavigationDestination("menu", R.drawable.ic_menu_24, R.string.menu)
    object Favorites :
        MainNavigationDestination("favorite", R.drawable.ic_favorite_24, R.string.favorites)

    object Cart : MainNavigationDestination("cart", R.drawable.ic_cart_24, R.string.cart)
    object Profile :
        MainNavigationDestination("profile", R.drawable.ic_profile_24, R.string.profile)

    object Orders : MainNavigationDestination("orders", R.drawable.ic_orders_24, R.string.orders)
    object Notifications : MainNavigationDestination(
        "notifications",
        R.drawable.ic_notifications_24,
        R.string.notifications
    )

    object About : MainNavigationDestination("about", R.drawable.ic_sb_24, R.string.about)
}