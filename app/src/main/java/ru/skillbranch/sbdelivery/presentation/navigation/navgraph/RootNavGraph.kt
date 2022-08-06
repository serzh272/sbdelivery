package ru.skillbranch.sbdelivery.presentation.navigation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.skillbranch.sbdelivery.presentation.login.SignInScreen
import ru.skillbranch.sbdelivery.presentation.login.SignInViewModel
import ru.skillbranch.sbdelivery.presentation.login.SignUpScreen
import ru.skillbranch.sbdelivery.presentation.root.RootScreen
import ru.skillbranch.sbdelivery.presentation.root.RootViewModel
import ru.skillbranch.sbdelivery.presentation.splash.SplashScreen


@Composable
fun RootNavGraph(rootNavController: NavHostController, rootViewModel: RootViewModel) {
    NavHost(
        rootNavController,
        startDestination = RootNavigation.Root.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(RootNavigation.Root.route) {
            RootScreen(rootNavController, rootViewModel = rootViewModel)
        }
        composable(RootNavigation.Splash.route) {
            SplashScreen()
        }
        navigation(
            startDestination = LoginNavigation.SignIn.route,
            route = RootNavigation.Login.route
        ) {
            composable(LoginNavigation.SignIn.route) {
                val signInViewModel: SignInViewModel = hiltViewModel()
                SignInScreen(rootNavController, signInViewModel)
            }
            composable(LoginNavigation.SignUp.route) {
                SignUpScreen(rootNavController)
            }
        }
    }
}

sealed class RootNavigation(val route: String) {
    object Root : RootNavigation("root")
    object Splash : RootNavigation("splash")
    object Login : RootNavigation("login")
}

sealed class LoginNavigation(val route: String) {
    object SignIn : LoginNavigation("sign_in")
    object SignUp : LoginNavigation("sign_up")
}