package ru.skillbranch.sbdelivery.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.domain.model.DestinationWithCounter
import ru.skillbranch.sbdelivery.domain.model.DrawerState
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.MainNavigationDestination

class MainViewModel: ViewModel() {

    companion object{

        sealed class NavigationParam{
            data class Route(val route: String, val navOptions: NavOptions?,
                             val navigatorExtras: Navigator.Extras?): NavigationParam()
            data class Direction(val directions: NavDirections): NavigationParam()
            data class Builder(val route: String, val navOptionsBuilder: NavOptionsBuilder): NavigationParam()
            data class TopLevel(val route: String): NavigationParam()
        }
    }
    private val _drawerState: MutableStateFlow<DrawerState?> = MutableStateFlow(DrawerState(
        fullName = "Сидоров Иван",
        email = "serzh272@mail.ru",
        destinations = listOf(
            DestinationWithCounter(MainNavigationDestination.Main, isHomeDest = true),
            DestinationWithCounter(MainNavigationDestination.Menu),
            DestinationWithCounter(MainNavigationDestination.Favorites),
            DestinationWithCounter(MainNavigationDestination.Cart, counterValue = "+5"),
            DestinationWithCounter(MainNavigationDestination.Profile),
            DestinationWithCounter(MainNavigationDestination.Orders),
            DestinationWithCounter(MainNavigationDestination.Notifications),
            DestinationWithCounter(MainNavigationDestination.About, inBottomSection = true),
        )
    ))
    val drawerState:StateFlow<DrawerState?> = _drawerState

    private val _navigationParams: MutableSharedFlow<NavigationParam> = MutableSharedFlow()
    val navigationParams: SharedFlow<NavigationParam> = _navigationParams

    fun navigateTo(route: NavigationParam){
        viewModelScope.launch {
            _navigationParams.emit(route)
        }
    }
}