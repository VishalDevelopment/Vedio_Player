package com.example.vedioplayer.Ui_Layer.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.vedioplayer.Ui_Layer.Screens.HomeScreen
import com.example.vedioplayer.Ui_Layer.Screens.PlayerScreen
import com.example.vedioplayer.Ui_Layer.Screens.VedioScreen
import kotlinx.serialization.Serializable

data class BottomBarOptions(
    val name: String,
    val SelectedIcon: ImageVector,
    val UnselectedIcon: ImageVector,
)

@Composable
@Preview
fun AppNavigation() {
    val navController = rememberNavController()
    val controlList = mutableListOf(
        BottomBarOptions(
            "home",
            Icons.Default.Home,
            Icons.Outlined.Home
        ),
        BottomBarOptions(
            "player",
            Icons.Default.PlayArrow,
            Icons.Outlined.PlayArrow
        )
    )

    val selectedIcon = remember {
        mutableStateOf(Icons.Default.Home)
    }


    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier.height(60.dp), windowInsets = WindowInsets(0.dp)) {
                IconButton(onClick = {
                    selectedIcon.value = Icons.Default.Home
                    navController.navigate(NavigationRoute.HomeScreen) {
                        launchSingleTop = true
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(imageVector =if (selectedIcon.value == controlList[0].SelectedIcon){
                        controlList[0].SelectedIcon
                    }
                    else{
                    controlList[0].UnselectedIcon
                    }, contentDescription = null)
                }

                IconButton(onClick = {
                    selectedIcon.value = Icons.Default.PlayArrow
                    navController.navigate(NavigationRoute.VedioScreen) {
                        launchSingleTop = true
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(imageVector =if (selectedIcon.value == controlList[1].SelectedIcon){
                        controlList[1].SelectedIcon
                    }
                    else{
                        controlList[1].UnselectedIcon
                    }, contentDescription = null)
                }

            }
        }
    ) {
        Box(modifier = Modifier.padding(it)){

            NavHost(navController = navController, startDestination = NavigationRoute.HomeScreen) {
                composable<NavigationRoute.HomeScreen> {
                    HomeScreen()
                }
                composable<NavigationRoute.VedioScreen> {
                    VedioScreen(selectedIcon,navController)
                }
                composable<NavigationRoute.PlayerScreen>{
                    val VedioPath = it.toRoute<NavigationRoute.PlayerScreen>()
                   PlayerScreen(VedioPath)
                }
            }

        }
    }
}

sealed class NavigationRoute {
    @Serializable
    object HomeScreen : NavigationRoute()
    @Serializable
    data class PlayerScreen(val vedioUri: String) : NavigationRoute()
    @Serializable
    object VedioScreen:NavigationRoute()
}
