package ru.skillbranch.sbdelivery.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.presentation.component.TiledSurface

@Preview
@Composable
fun SplashScreen() {
    TiledSurface(tileRes = R.drawable.tile_background,
    modifier = Modifier.fillMaxSize()) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo_sb_delivery),
            contentDescription = "SB Delivery Logo",
            modifier = Modifier.padding(96.dp)
        )
    }
}