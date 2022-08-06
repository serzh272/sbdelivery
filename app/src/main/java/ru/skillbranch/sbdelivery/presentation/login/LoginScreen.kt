package ru.skillbranch.sbdelivery.presentation.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.data.datasource.LoadResult
import ru.skillbranch.sbdelivery.presentation.component.tileBackground
import ru.skillbranch.sbdelivery.presentation.navigateWithClearingBackStack
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavigation

@Composable
fun SignInScreen(rootNavController: NavHostController, signInViewModel: SignInViewModel = viewModel()) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .tileBackground(R.drawable.tile_background, LocalContext.current.resources)
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.email), fontSize = 12.sp, color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )
        OutlinedTextField(
            value = email,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
            ),
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1
        )
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        Text(
            text = stringResource(R.string.password), fontSize = 12.sp, color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, top = 16.dp)
        )
        OutlinedTextField(
            value = password,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White
            ),
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_24),
                        contentDescription = ""
                    )
                }
            },
            maxLines = 1
        )
        Button(
            onClick = {
                scope.launch {
                    signInViewModel.signIn(email, password).collect{
                        when(it){
                            is LoadResult.Success -> {
                                rootNavController.navigateWithClearingBackStack(popToRoot = RootNavigation.Login.route, route = RootNavigation.Root.route)
                            }
                            is LoadResult.Error -> {
                                Log.d("M_LoginScreen", "Error")
                            }
                            is LoadResult.Load -> {Log.d("M_LoginScreen", "Load")}
                        }
                    }
                }


            },
            modifier = Modifier
                .padding(top = 45.dp)
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.forgot_password),
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .clickable {
                    /*TODO*/
                }
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SignUpScreen(navController: NavHostController) {
    Text(
        text = "Sign Up",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}