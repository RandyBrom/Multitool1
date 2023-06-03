package ru.juraogurcov.multitool

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import navigation.MyNavHostController

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val composeBottomNavigation = rememberNavController()
            Scaffold(
                bottomBar = { navigation.MyBottomNavigation(navHostController = composeBottomNavigation) }
            ) {
                MyNavHostController(navHostController = composeBottomNavigation)
            }
        }
    }

}



