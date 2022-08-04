package ru.skillbranch.sbdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavGraph
import ru.skillbranch.sbdelivery.presentation.ui.theme.SBDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBDeliveryTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                RootNavGraph(rootNavController = navController)
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    SBDeliveryTheme {
        val navController = rememberNavController()
        RootNavGraph(rootNavController = navController)
    }
}