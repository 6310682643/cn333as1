package com.example.numberguessinggame

import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme
import kotlin.random.Random.Default.nextInt
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import kotlin.random.Random

class MainActivity : ComponentActivity() {

//    private var random: Int = nextInt(1, 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NumberGuessingGameScreen()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NumberGuessingGameScreen() {
    var random by remember { mutableStateOf((1..1000).random()) }
    var text by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var guessCount by remember { mutableStateOf(0) }
//    var playing = remember { mutableStateOf(true) }

    Text(
        text = "Number Guessing Game",
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF6200ED))
            .padding(15.dp)
    )
    Column(
        modifier = Modifier.padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.header),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.your_name)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = {
                guessCount++
                result = ""
                val userGuess = text.toInt()

                if (userGuess != null) {
                     if (userGuess == random) {
                         result = "Correct! You guessed the number in $guessCount time!"
                         text = ""
                         random = (1..1000).random()
                         guessCount = 0
//                         playing.value = false

                    } else if (userGuess > random) {
                        result = "Hint: It's Lower!"
                        text = ""

                    } else {
                         result = "Hint: It's Higher!"
                         text = ""
                    }
                }else {
                    result = "Please enter a valid number"
                }
            }
        ) {
            Text(text = "play again")

//            if (playing.value) {
//                Text(text = "Guess")
//            }
//            else {
//                Text(text = "play again")
//                random = (1..1000).random()
//                guessCount = 0
//                result = ""
//            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = result,
            color = androidx.compose.ui.graphics.Color.Gray,
            fontSize = 20.sp,
        )
    }

}



//@Composable
//fun PlayAgainButton() {
//    Button(
//        onClick = {
//            answer = Random.nextInt(1, 1000)
//            guessCount = 0
//            playing = true
//        },
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Text("Play Again")
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NumberGuessingGameTheme {
//        Text(text = "Number Guessing Game")
//        NumberGuessingGameScreen()
//    }
//}