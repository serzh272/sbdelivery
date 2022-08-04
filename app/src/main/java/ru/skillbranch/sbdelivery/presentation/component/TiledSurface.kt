package ru.skillbranch.sbdelivery.presentation.component

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext

@Composable
fun TiledSurface(modifier: Modifier = Modifier, @DrawableRes tileRes: Int, content: @Composable () -> Unit) =
    Surface(
        color = Color.Transparent,
        modifier = modifier
            .tileBackground(tileRes, LocalContext.current.resources)
    ) {
        content()
    }

fun Modifier.tileBackground(@DrawableRes drawableRes: Int, localResources: Resources): Modifier {
    val option = BitmapFactory.Options()
    option.inPreferredConfig = Bitmap.Config.ARGB_8888
    val pattern = BitmapFactory.decodeResource(
        localResources,
        drawableRes,
        option
    ).asImageBitmap()
    return clipToBounds()
            .drawBehind {
        val paint = Paint().asFrameworkPaint().apply {
            shader = ImageShader(pattern, TileMode.Repeated, TileMode.Repeated)
        }
        drawIntoCanvas {
            it.nativeCanvas.drawPaint(paint)
            paint.reset()
        }
    }
}