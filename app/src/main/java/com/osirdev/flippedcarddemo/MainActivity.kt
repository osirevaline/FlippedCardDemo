package com.osirdev.flippedcarddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.osirdev.flippedcarddemo.ui.theme.FlippedCardDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlippedCardDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FlippedCardDemoTheme {
                        FlippedCard(color = 0xff91a4fc)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlippedCardDemoTheme {
        FlippedCard(color = 0xff91a4fc)
    }
}

@Composable
fun FlippedCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    color: Long
) {
    Box(
        modifier = modifier.padding(10.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val path = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(path) {
                drawRoundRect(
                    color = Color(color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(color.toInt(), 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Compose is Awesome",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum fringilla, est sit amet auctor consequat, nulla risus porttitor nunc, quis mollis sem metus ac ipsum. Nullam hendrerit quam vitae nulla scelerisque, nec congue enim rutrum. In sollicitudin pellentesque sapien, et lacinia magna lacinia sed. Etiam scelerisque venenatis rutrum.",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }
    }
}