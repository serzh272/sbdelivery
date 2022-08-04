package ru.skillbranch.sbdelivery.presentation.navigation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.skillbranch.sbdelivery.presentation.main.AppScreen
import ru.skillbranch.sbdelivery.presentation.navigation.login.SignInScreen
import ru.skillbranch.sbdelivery.presentation.navigation.login.SignUpScreen
import ru.skillbranch.sbdelivery.presentation.splash.SplashScreen

const val ROOT_ROUTE = "root"

@Composable
fun RootNavGraph(rootNavController: NavHostController) {
    NavHost(rootNavController, startDestination = RootNavigation.Main.route, Modifier.fillMaxSize()) {
        composable(RootNavigation.Main.route) { AppScreen(rootNavController) }
        composable(RootNavigation.Splash.route) { SplashScreen(rootNavController) }
        navigation(startDestination = LoginNavigation.SignIn.route, route = ROOT_ROUTE) {
            composable(LoginNavigation.SignIn.route) { SignInScreen(rootNavController) }
            composable(LoginNavigation.SignUp.route) { SignUpScreen(rootNavController) }
        }
    }
}

sealed class RootNavigation(val route: String) {
    object Main : RootNavigation("main")
    object Splash : RootNavigation("splash")
}

sealed class LoginNavigation(val route: String) {
    object SignIn : LoginNavigation("sign_in")
    object SignUp : LoginNavigation("sign_up")
}