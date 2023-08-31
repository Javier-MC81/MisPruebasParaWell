package com.jmoreno.mispruebasparawell.Second

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jmoreno.mispruebasparawell.ui.theme.MisPruebasParaWellTheme
import live.wellconnect.wellconnect.presentation.SignInState
import androidx.navigation.NavController
import com.jmoreno.mispruebasparawell.OnBoardingSecondContent

class OnBoardingSecond : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MisPruebasParaWellTheme {
                val navigateBack: () -> Unit = {
                    onBackPressed()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardingSecondContent(

                        navController = NavController(this),
                        state = SignInState(false,""),
                        navigateBack
                    )
                }
        }
        }
    }
}
