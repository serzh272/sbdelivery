package ru.skillbranch.sbdelivery.presentation.root

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.presentation.component.DishesSection
import ru.skillbranch.sbdelivery.presentation.component.DrawerComponent
import ru.skillbranch.sbdelivery.presentation.home.HomeViewModel
import ru.skillbranch.sbdelivery.presentation.main.MainViewModel
import ru.skillbranch.sbdelivery.presentation.navigateWithClearingBackStack
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.MainNavGraph
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavigation

@Composable
fun RootScreen(
    rootNavController: NavHostController,
    viewModel: MainViewModel = viewModel(),
    rootViewModel: RootViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState by viewModel.drawerState.collectAsState()
    val userData by rootViewModel.userData.collectAsState()
    BackHandler(scaffoldState.drawerState.isOpen) {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Main", fontSize = 22.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_burger_24),
                            contentDescription = "Hamburger menu button"
                        )
                    }
                }
            )
        },
        drawerContent = {
            DrawerComponent(navController,
                drawerState = drawerState?.copy(
                    fullName = "${userData?.firstName ?: ""} ${userData?.lastName ?: ""}",
                    email = userData?.email ?: ""
                ),
                onLogoutClick = {
                    scope.launch {
                        rootViewModel.logout()
                        rootNavController.navigateWithClearingBackStack(route = RootNavigation.Login.route)
                    }
                },
                onItemClick = {
                    scope.launch {
                        if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        MainNavGraph(navController = navController, modifier = Modifier.padding(it))
    }
}

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.wallpaper),
            contentDescription = "Wallpaper",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
                .aspectRatio(1.8f)
                .clip(RoundedCornerShape(8.dp))
        )
        DishesSection(sectionName = "Рекомендуем")
        DishesSection(sectionName = "Лучшее")
        DishesSection(sectionName = "Популярное")
    }
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

@Composable
fun AboutScreen(navController: NavHostController) {
    Text(
        text = "About Screen",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}