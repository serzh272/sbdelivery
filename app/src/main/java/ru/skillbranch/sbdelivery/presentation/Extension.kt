package ru.skillbranch.sbdelivery.presentation

import androidx.navigation.NavHostController
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavigation


fun NavHostController.navigateWithClearingBackStack(popToRoot: String = RootNavigation.Root.route, route: String){
    popBackStack(popToRoot, true)
    navigate(route)
}