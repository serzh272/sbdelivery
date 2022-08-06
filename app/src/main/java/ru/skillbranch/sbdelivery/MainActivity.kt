package ru.skillbranch.sbdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.skillbranch.sbdelivery.data.datasource.LoadResult
import ru.skillbranch.sbdelivery.presentation.navigateWithClearingBackStack
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavGraph
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavigation
import ru.skillbranch.sbdelivery.presentation.root.RootViewModel
import ru.skillbranch.sbdelivery.presentation.ui.theme.SBDeliveryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBDeliveryTheme {
                val rootNavController = rememberNavController()
                val rootViewModel = viewModel<RootViewModel>()
                val loadResultFlow by rootViewModel.profileFlow.collectAsState(initial = LoadResult.Load())
                loadResultFlow.let { res ->
                    when(res){
                        is LoadResult.Load -> {
                            LaunchedEffect(Unit){
                                rootNavController.navigate(RootNavigation.Splash.route)
                            }
                        }
                        is LoadResult.Success -> {
                            LaunchedEffect(res.data){
                                rootNavController.navigateUp()
                            }
                        }
                        is LoadResult.Error -> {
                            LaunchedEffect(res.error){
                                rootNavController.navigateWithClearingBackStack(route = RootNavigation.Login.route)
                            }
                        }
                    }
                }
                RootNavGraph(rootNavController, rootViewModel)
            }
        }
    }
}