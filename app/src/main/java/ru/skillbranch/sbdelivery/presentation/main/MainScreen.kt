package ru.skillbranch.sbdelivery.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.presentation.navigation.RootNavigation

@Composable
fun MainScreen(rootNavController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_burger_24),
                        contentDescription = "Navigate Up Button"
                    )
                }
                Text(text = "Main", fontSize = 22.sp)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(navController, startDestination = RootNavigation.Main.route, Modifier.padding(it)) {
            composable(RootNavigation.Main.route) { MainScreen(navController) }
        }
    }
}