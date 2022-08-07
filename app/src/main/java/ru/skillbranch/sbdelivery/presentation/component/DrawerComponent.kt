package ru.skillbranch.sbdelivery.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.domain.model.DestinationWithCounter
import ru.skillbranch.sbdelivery.domain.model.DrawerState
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.MainNavigationDestination
import ru.skillbranch.sbdelivery.presentation.ui.theme.SBDeliveryTheme


@Composable
fun DrawerComponent(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState?,
    onLogoutClick: () -> Unit,
    onItemClick: ((MainNavigationDestination) -> Unit)? = null
) = Column(
    modifier = Modifier
        .fillMaxSize()
) {
    Row(
        Modifier
            .tileBackground(R.drawable.tile_background, LocalContext.current.resources)
            .padding(20.dp)
            .height(170.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .align(Alignment.Bottom)
        ) {
            Text(text = drawerState?.fullName ?: "Noname")
            drawerState?.email?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { onLogoutClick() },
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .align(Alignment.Bottom)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout_24),
                contentDescription = "Navigate Up Button"
            )
        }
    }
    Column(
        Modifier
            .background(Color(LocalContext.current.getColor(R.color.main)))
            .fillMaxSize()
            .padding(bottom = 12.dp)
    ) {
        drawerState?.destinations?.groupBy { it.inBottomSection }?.let {
            val drawerItemCall: @Composable (dest: DestinationWithCounter) -> Unit = { dest ->
                DrawerItem(
                    text = stringResource(id = dest.destination.nameRes),
                    iconRes = dest.destination.iconRes,
                    counterText = dest.counterValue,
                    onClick = {
                        if (dest.isHomeDest) {
                            navController.popBackStack(dest.destination.route, false)
                        } else {
                            navController.navigate(
                                route = dest.destination.route
                            ) {
                                launchSingleTop = true
                                popUpTo(MainNavigationDestination.Main.route) {
                                    inclusive = false
                                }
                            }
                        }
                        onItemClick?.invoke(dest.destination)
                    })
            }
            it[false]?.forEach { dest ->
                drawerItemCall(dest)
            }
            it[true]?.let { destinations ->
                Spacer(modifier = Modifier.weight(1f))
                destinations.forEach { dest ->
                    drawerItemCall(dest)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DrawerPreview() {
    SBDeliveryTheme() {
        DrawerComponent(
            drawerState = DrawerState(
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
            ),
            onLogoutClick = {/*TODO*/ }
        )
    }
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconRes: Int,
    counterText: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier
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