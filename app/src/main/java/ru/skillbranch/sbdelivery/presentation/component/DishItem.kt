package ru.skillbranch.sbdelivery.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.domain.model.DishModel

@Preview
@Composable
fun DishItem(dishModel: DishModel? = null, onItemClick: ((DishModel) -> Unit)? = null, onItemAddButtonClick: ((DishModel) -> Unit)? = null) {
    Box(
        modifier = Modifier
            .size(158.dp, 220.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    if (dishModel != null) {
                        onItemClick?.invoke(dishModel)
                    }
                }
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                imageModel = dishModel?.image,
                contentScale = ContentScale.Crop,
                previewPlaceholder = R.drawable.wallpaper,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                    .aspectRatio(1f)
            )
            Text(text = dishModel?.price?.let { "$it Р" } ?: "",
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.wrapContentWidth(align = Alignment.Start))
            Text(
                text = dishModel?.name ?: "",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.Start)
                    .padding(bottom = 12.dp)
            )
        }
        if (dishModel?.promotion == true){
            Text(
                text = "АКЦИЯ",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(topEnd = 3.dp, bottomEnd = 3.dp))
                    .background(Color.Yellow)
                    .padding(horizontal = 10.dp, vertical = 2.dp)
            )
        }
        FloatingActionButton(
            onClick = {
                if (dishModel != null) {
                    onItemAddButtonClick?.invoke(dishModel)
                }
            },
            contentColor = MaterialTheme.colors.onPrimary,
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 42.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_24),
                contentDescription = "Add Icon"
            )
        }
    }
}