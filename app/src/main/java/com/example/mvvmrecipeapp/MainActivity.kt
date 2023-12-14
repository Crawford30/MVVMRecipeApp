package com.example.mvvmrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.mvvmrecipeapp.ui.theme.MVVMRecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import javax.inject.Inject

//class MainActivity : ComponentActivity() {

/**
 * Using Fragment with composable
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someRandomString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



//        setContent {
//            Column {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Gray)
//                        .height(200.dp)
//                        .border(BorderStroke(1.dp, Color.Red)),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        "Happy Coding",
//                        Modifier.align(Alignment.CenterHorizontally)
//                    )
//                }
//                Spacer(modifier = Modifier.padding(20.dp))
//
//                Row(
//                    modifier = Modifier
//                        .width(200.dp)
//                        .background(Color.Gray)
//                        .height(200.dp)
//                        .border(BorderStroke(1.dp, Color.Red)),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        "Happy Coding",
//                        Modifier.align(Alignment.CenterVertically)
//                    )
//
//                }
//
//            }
//
//        }

//        setContent {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Gray)
//                    .verticalScroll(
//                        rememberScrollState(),
//                    )
//            ) {
//
//                Image(
//                    painter = painterResource(id = R.drawable.happy_meal_small),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .height(300.dp)
//                        .fillMaxWidth(),
//                    contentScale = ContentScale.Crop,
//                )
//
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = "Happy Meal",
//                            style = TextStyle(
//                                fontSize = 26.sp
//                            ),
//                            modifier = Modifier.align(
//                                Alignment.CenterVertically
//                            )
//                        )
//
//                        Text(
//                            text = "$5.9",
//                            color = Color(0xFF85bb65),
//                            style = TextStyle(
//                                fontSize = 15.sp
//                            )
//                        )
//
//
//                    }
//
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//
//                    Text(
//                        text = "800 Calories",
//                        style = TextStyle(
//                            fontSize = 17.sp
//                        )
//                    )
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//
//                    Button(onClick = { }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                        Text(text = "ORDER NOW")
//                    }
//                }
//            }
//
//
//        }
//            MVVMRecipeAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMRecipeAppTheme {
        Greeting("Android")
    }
}