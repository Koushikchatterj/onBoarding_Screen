package com.example.firstpageonetime1

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstpageonetime1.ui.theme.Firstpageonetime1Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        viewModel = ViewModelProvider (this)[MainViewModel::class.java]
        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading
        }

        enableEdgeToEdge()
        setContent {
            Firstpageonetime1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun MyApp(viewModel: MainViewModel, modifier: Modifier) {
    val navController = rememberNavController()
    val isFirstPageShown = viewModel.isFirstPageShown()

    NavHost(navController = navController, startDestination = if (isFirstPageShown) "second" else "first") {
        composable("first") { FirstPage(navController, viewModel) }
        composable("second") { SecondPage() }
    }
}

@Composable
fun FirstPage(navController: NavController, viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("second")
            viewModel.setFirstPageShown()
        }) {
            Text("onBording screen")
        }
    }
}

@Composable
fun SecondPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "This is the second page.")
    }
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences =
        application.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(100)
            isLoading = false
        }
    }

    fun isFirstPageShown(): Boolean {
        return sharedPreferences.getBoolean("FIRST_PAGE_SHOWN", false)
    }

    fun setFirstPageShown() {
        sharedPreferences.edit().putBoolean("FIRST_PAGE_SHOWN", true).apply()
    }
}