package ru.skillbranch.sbdelivery.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.presentation.component.DrawerComponent
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.MainNavGraph

@Composable
fun AppScreen(rootNavController: NavHostController, viewModel: RootViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                IconButton(onClick = {
                    scope.launch{
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_burger_24),
                        contentDescription = "Navigate Up Button"
                    )
                }
                Text(text = "Main", fontSize = 22.sp)
            }
        },
        drawerContent = {
            DrawerComponent(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        MainNavGraph(navController = navController, modifier = Modifier.padding(it))
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Text(
        text = "Main Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun MenuScreen(navController: NavHostController) {
    Text(
        text = "Menu Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun FavoritesScreen(navController: NavHostController) {
    Text(
        text = "Favorites Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun CartScreen(navController: NavHostController) {
    Text(
        text = "Cart Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Text(
        text = "Profile Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun OrdersScreen(navController: NavHostController) {
    Text(
        text = "Orders Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    Text(
        text = "Notifications Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}