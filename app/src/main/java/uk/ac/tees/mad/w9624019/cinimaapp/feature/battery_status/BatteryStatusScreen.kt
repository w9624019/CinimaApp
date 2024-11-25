package uk.ac.tees.mad.w9624019.cinimaapp.feature.battery_status

import android.content.Context
import android.os.BatteryManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BatteryStatusScreen(context: Context) {

    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // on below line we are displaying
        // a text as volume controller.
        Text(
            text = "Battery Level Indicator",
            // on below line we are adding font weight
            fontWeight = FontWeight.Bold,

            // on below line we are adding font size.
            fontSize = 20.sp,

            // on below line we are adding text color.
            color = Color.Cyan,
        )

        // Call battery manager service
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        // Get the battery percentage and store it in a INT variable
        val batLevel: Int = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

        // on below line we are creating
        // text to display battery level.
        Text(
            text = "Battery Level : "+batLevel,
            // on below line we are
            // adding font weight.
            fontWeight = FontWeight.Bold,

            // on below line we are adding
            // font size and color.
            fontSize = 20.sp,
            color = Color.Cyan
        )
    }
}


