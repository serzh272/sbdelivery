package ru.skillbranch.sbdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ru.skillbranch.sbdelivery.presentation.main.MainScreen
import ru.skillbranch.sbdelivery.presentation.navigation.RootNavigation
import androidx.compose.ui.unit.dp
import ru.skillbranch.sbdelivery.presentation.component.TiledSurface
import ru.skillbranch.sbdelivery.presentation.ui.theme.SBDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBDeliveryTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController, startDestination = RootNavigation.Main.route, Modifier.fillMaxSize()) {
                    composable(RootNavigation.Main.route) { MainScreen(navController) }
                    composable(RootNavigation.Login.route) {/*TODO Place Splash Screen*/}
                }
            }
        }
    }
}
@Preview
@Composable
fun MainScreenPreview() {
    SBDeliveryTheme {
        val navController = rememberNavController()
        MainScreen(navController)
    }
}