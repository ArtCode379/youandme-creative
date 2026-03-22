package shop.youandmecreative.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import shop.youandmecreative.app.ui.composable.approot.AppRoot
import shop.youandmecreative.app.ui.theme.ProductAppYNMCRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppYNMCRTheme {
                AppRoot()
            }
        }
    }
}