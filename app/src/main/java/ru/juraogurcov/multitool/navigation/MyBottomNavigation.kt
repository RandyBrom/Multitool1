package navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import menu.BottomItem
import ru.juraogurcov.multitool.ui.more.ThirdScreen
import ru.juraogurcov.multitool.ui.person.FirstScreen
import ru.juraogurcov.multitool.ui.vpn.SecondScreen

@Composable
fun MyBottomNavigation(navHostController: NavHostController) {
    val listItems = listOf(
        BottomItem.FirstScreen,
        BottomItem.SecondScreen,
        BottomItem.ThirdScreen
    )
    androidx.compose.material.BottomNavigation(backgroundColor = Color.White) {
        val backStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            BottomNavigationItem(selected = currentRoute == item.route, onClick = {navHostController.navigate(item.route)}, icon = {
                Icon(
                    painter = painterResource(id = item.resId),
                    contentDescription = stringResource(id = item.titleId)
                )
            },
                label = {
                    Text(
                        text = stringResource(id = item.titleId),
                        fontSize = 10.sp
                    )
                },
                selectedContentColor = Color.Magenta,
                unselectedContentColor = Color.DarkGray
            )
        }


    }
}

@Composable
fun MyNavHostController(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "FirstScreen") {
        composable("FirstScreen") {
            FirstScreen()
        }

        composable("SecondScreen") {
            SecondScreen()
        }

        composable("ThirdScreen") {
            ThirdScreen()
        }
    }
}