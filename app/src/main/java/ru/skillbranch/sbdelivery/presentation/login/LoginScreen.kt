package ru.skillbranch.sbdelivery.presentation.login

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
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
import ru.skillbranch.sbdelivery.domain.model.UserModel.Companion.toUserModel
import ru.skillbranch.sbdelivery.presentation.component.tileBackground
import ru.skillbranch.sbdelivery.presentation.navigateWithClearingBackStack
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.LoginNavigation
import ru.skillbranch.sbdelivery.presentation.navigation.navgraph.RootNavigation
import ru.skillbranch.sbdelivery.presentation.root.RootViewModel

@Composable
fun SignInScreen(
    rootNavController: NavHostController,
    signInViewModel: SignInViewModel = viewModel(),
    rootViewModel: RootViewModel
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var uiEnabled by rememberSaveable { mutableStateOf(true) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Column(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .tileBackground(R.drawable.tile_background, LocalContext.current.resources)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.enter), fontSize = 22.sp)
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        dispatcher?.onBackPressed()
                    }
                }) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_24),
                        contentDescription = "Navigate Up Button"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.email), fontSize = 12.sp, color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )
            OutlinedTextField(
                enabled = uiEnabled,
                value = email,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    placeholderColor = Color.Black,
                ),
                onValueChange = {
                    email = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.email_placeholder))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            Text(
                text = stringResource(R.string.password), fontSize = 12.sp, color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 16.dp)
            )
            OutlinedTextField(
                enabled = uiEnabled,
                value = password,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    placeholderColor = Color.Black,
                ),
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.password_placeholder))
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
                singleLine = true
            )
            Button(
                enabled = uiEnabled,
                onClick = {
                    scope.launch {
                        signInViewModel.signIn(email, password).collect {
                            when (it) {
                                is LoadResult.Success -> {
                                    rootNavController.navigateWithClearingBackStack(
                                        popToRoot = RootNavigation.Login.route,
                                        route = RootNavigation.Root.route
                                    )
                                    rootViewModel.setUserData(it.data.toUserModel())
                                }
                                is LoadResult.Error -> {
                                    uiEnabled = true
                                }
                                is LoadResult.Load -> {
                                    uiEnabled = false
                                }
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
            OutlinedButton(
                enabled = uiEnabled,
                onClick = { rootNavController.navigate(LoginNavigation.SignUp.route){
                    launchSingleTop = true
                } },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.onPrimary,
                ),
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
                        if (uiEnabled) {
                            //TODO
                        }
                    }
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun SignUpScreen(
    rootNavController: NavHostController,
    signUpViewModel: SignUpViewModel = viewModel()
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var uiEnabled by rememberSaveable { mutableStateOf(true) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Column(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .tileBackground(R.drawable.tile_background, LocalContext.current.resources)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.enter), fontSize = 22.sp)
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        dispatcher?.onBackPressed()
                    }
                }) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_24),
                        contentDescription = "Navigate Up Button"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.first_name), fontSize = 12.sp, color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )
            OutlinedTextField(
                enabled = uiEnabled,
                value = firstName,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    placeholderColor = Color.Black,
                ),
                onValueChange = {
                    firstName = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.name_placeholder))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )
            Text(
                text = stringResource(R.string.last_name), fontSize = 12.sp, color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 16.dp)
            )
            OutlinedTextField(
                enabled = uiEnabled,
                value = lastName,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    placeholderColor = Color.Black,
                ),
                onValueChange = {
                    lastName = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.last_name_placeholder))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )
            Text(
                text = stringResource(R.string.email), fontSize = 12.sp, color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 16.dp)
            )
            OutlinedTextField(
                enabled = uiEnabled,
                value = email,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    placeholderColor = Color.Black,
                ),
                onValueChange = {
                    email = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.email_placeholder))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            Text(
                text = stringResource(R.string.password), fontSize = 12.sp, color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, top = 16.dp)
            )
            OutlinedTextField(
                enabled = uiEnabled,
                value = password,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    placeholderColor = Color.Black,
                ),
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.password_placeholder))
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
                singleLine = true
            )
            Button(
                enabled = uiEnabled,
                onClick = {
                    scope.launch {
                        signUpViewModel.signUp(firstName, lastName, email, password).collect {
                            when (it) {
                                is LoadResult.Success -> {
                                    rootNavController.navigateWithClearingBackStack(
                                        popToRoot = RootNavigation.Login.route,
                                        route = RootNavigation.Root.route
                                    )
                                }
                                is LoadResult.Error -> {
                                    uiEnabled = true
                                }
                                is LoadResult.Load -> {
                                    uiEnabled = false
                                }
                            }
                        }
                    }


                },
                modifier = Modifier
                    .padding(top = 45.dp)
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = stringResource(id = R.string.register))
            }
            OutlinedButton(
                enabled = uiEnabled,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.onPrimary,
                ),
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = stringResource(id = R.string.enter))
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}