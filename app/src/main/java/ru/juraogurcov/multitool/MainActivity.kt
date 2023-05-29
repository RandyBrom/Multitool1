package ru.juraogurcov.multitool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val composeBottomNavigation = rememberNavController()
            BottomNavigation() {

                NavHost(
                    navController = composeBottomNavigation,
                    startDestination = "ProfileScreen"
                ) {

                    composable("ProfileScreen") {
                        mainScreen(composeBottomNavigation = composeBottomNavigation)

                    }

                    composable("VPNScreen") {
                        Text(text = "VPN")
                        Button(onClick = { composeBottomNavigation.navigate("VPNScreen") }) {}
                    }

                    composable("MoreScreen") {
                        Text(text = "More")
                        Button(onClick = { composeBottomNavigation.navigate("MoreScreen") }) {}
                    }
                }
            }
        }

    }

    @Preview
    @Composable
    fun mainScreen(composeBottomNavigation: NavHostController) {
        Text(text = "Profile")
        Button(onClick = { composeBottomNavigation.navigate("ProfileScreen") }) {
        }


    }
}