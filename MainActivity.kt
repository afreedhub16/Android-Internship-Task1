package com.example.helloandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.helloandroid.ui.theme.HelloAndroidTheme
import androidx.compose.runtime.LaunchedEffect
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HelloAndroidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { _ ->
                    RestaurantLoginScreen()
                }
            }
        }
    }
}

@Composable
fun RestaurantLoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var postTitle by remember { mutableStateOf("Loading API Data...") }

    LaunchedEffect(Unit) {
        try {
            val posts = RetrofitInstance.api.getPosts()
            postTitle = posts[0].title
        } catch (e: Exception) {
            postTitle = "API Error"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "🍽 Restaurant Login")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {}) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Total Orders: 120")
        Text(text = "Total Customers: 85")
        Text(text = "Today's Revenue: ₹15,000")

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "API Data:")
        Text(text = postTitle)

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {}) {
            Text("Dashboard")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {}) {
            Text("View Menu")
        }
    }
}