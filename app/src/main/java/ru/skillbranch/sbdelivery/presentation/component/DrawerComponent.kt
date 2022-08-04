package ru.skillbranch.sbdelivery.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.MainNavigation
import ru.skillbranch.sbdelivery.presentation.ui.theme.SBDeliveryTheme


@Composable
fun DrawerComponent(navController: NavHostController = rememberNavController()) = Column(
    modifier = Modifier
        .fillMaxSize()
) {
    Row(
        Modifier
            .height(170.dp)
            .fillMaxWidth()
            .tileBackground(R.drawable.tile_background, LocalContext.current.resources)
    ) {
        Text(text = "Сидоров Иван")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(LocalContext.current.getColor(R.color.main)))
    ) {
        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .build()
        DrawerItem(text = "Home", iconRes = R.drawable.ic_home_24, counterText = null, onClick = {
            navController.popBackStack(route = MainNavigation.Main.route, inclusive = false)
        })
        DrawerItem(text = "Menu", iconRes = R.drawable.ic_menu_24, counterText = null, onClick = {
            navController.navigate(
                route = MainNavigation.Menu.route,
                navOptions = navOptions
            )
        })
        DrawerItem(
            text = "Favorites",
            iconRes = R.drawable.ic_favorite_24,
            counterText = null,
            onClick = {
                navController.navigate(
                    route = MainNavigation.Favorites.route,
                    navOptions = navOptions
                )
            })
        DrawerItem(text = "Cart", iconRes = R.drawable.ic_cart_24, counterText = null, onClick = {
            navController.navigate(
                route = MainNavigation.Cart.route,
                navOptions = navOptions
            )
        })
        DrawerItem(
            text = "Profile",
            iconRes = R.drawable.ic_profile_24,
            counterText = null,
            onClick = {
                navController.navigate(
                    route = MainNavigation.Profile.route,
                    navOptions = navOptions
                )
            })
        DrawerItem(
            text = "Orders",
            iconRes = R.drawable.ic_orders_24,
            counterText = null,
            onClick = {
                navController.navigate(
                    route = MainNavigation.Orders.route,
                    navOptions = navOptions
                )
            })
        DrawerItem(
            text = "Notifications",
            iconRes = R.drawable.ic_notifications_24,
            counterText = null,
            onClick = {
                navController.navigate(
                    route = MainNavigation.Notifications.route,
                    navOptions = navOptions
                )
            })
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DrawerPreview() {
    SBDeliveryTheme() {
        DrawerComponent()
    }
}

@Composable
fun DrawerItem(
    text: String,
    @DrawableRes iconRes: Int,
    counterText: String? = null,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .clickable {
                onClick()
            }
            .height(48.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconRes),
            contentDescription = "Home Icon",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 20.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 32.dp)

        )
        Spacer(modifier = Modifier.weight(1f))
        if (counterText != null) {
            Text(
                text = counterText,
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp)

            )
        }
    }
}