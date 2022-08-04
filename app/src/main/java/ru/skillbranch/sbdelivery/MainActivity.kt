package ru.skillbranch.sbdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.skillbranch.sbdelivery.presentation.component.TiledSurface
import ru.skillbranch.sbdelivery.presentation.ui.theme.SBDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    drawerContent = {
                        
                    }
                ) {
                    TiledSurface(
                        tileRes = R.drawable.tile_background,
                        modifier = Modifier.padding(it)
                    ) {
                        Text("Android")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    SBDeliveryTheme {
        // A surface container using the 'background' color from the theme
        TiledSurface(
            tileRes = R.drawable.tile_background,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Android",
                modifier = Modifier
                    .padding(40.dp)
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            )
        }
    }
}


