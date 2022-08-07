package ru.skillbranch.sbdelivery.domain.model

import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.MainNavigationDestination

data class DrawerState(
    val fullName: String,
    val email: String? = null,
    val destinations: List<DestinationWithCounter> = listOf() //Ключ - экземпляр MainNavigation, значение -
)

data class DestinationWithCounter(
    val destination: MainNavigationDestination,
    val isHomeDest: Boolean = false,
    val inBottomSection: Boolean = false,
    val counterValue: String? = null
)
