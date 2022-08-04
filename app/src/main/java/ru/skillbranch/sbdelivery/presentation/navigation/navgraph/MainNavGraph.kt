package ru.skillbranch.sbdelivery.presentation.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.skillbranch.sbdelivery.presentation.main.*

@Composable
fun MainNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController, startDestination = MainNavigation.Main.route, modifier = modifier) {
        composable(MainNavigation.Main.route) { MainScreen(navController) }
        composable(MainNavigation.Menu.route) { MenuScreen(navController) }
        composable(MainNavigation.Favorites.route) { FavoritesScreen(navController) }
        composable(MainNavigation.Cart.route) { CartScreen(navController) }
        composable(MainNavigation.Profile.route) { ProfileScreen(navController) }
        composable(MainNavigation.Orders.route) { OrdersScreen(navController) }
        composable(MainNavigation.Notifications.route) { NotificationsScreen(navController) }
    }
}

sealed class MainNavigation(val route: String) {
    object Main : MainNavigation("main")
    object Menu : MainNavigation("menu")
    object Favorites : MainNavigation("favorite")
    object Cart : MainNavigation("cart")
    object Profile : MainNavigation("profile")
    object Orders : MainNavigation("orders")
    object Notifications : MainNavigation("notifications")
}