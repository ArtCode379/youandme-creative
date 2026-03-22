package shop.youandmecreative.app.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Support
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.youandmecreative.app.R
import shop.youandmecreative.app.ui.theme.Accent
import shop.youandmecreative.app.ui.theme.Divider
import shop.youandmecreative.app.ui.theme.MutedText
import shop.youandmecreative.app.ui.theme.OnSurface
import shop.youandmecreative.app.ui.theme.Primary

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
    ) {
        // Company info
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "COMPANY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Accent,
                    letterSpacing = 1.5.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.company_name),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = OnSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Version ${stringResource(R.string.app_version)}",
                    fontSize = 13.sp,
                    color = MutedText,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Settings options
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column {
                // Customer Support
                SettingsRow(
                    icon = Icons.Outlined.Support,
                    label = stringResource(R.string.settings_screen_customer_support_label),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.customer_support_link)))
                        context.startActivity(intent)
                    }
                )

                HorizontalDivider(color = Divider, modifier = Modifier.padding(horizontal = 16.dp))

                // Notifications
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = MutedText,
                    )
                    Text(
                        text = stringResource(R.string.settings_screen_notifications_label),
                        fontSize = 15.sp,
                        color = OnSurface,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                    )
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Primary,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Divider,
                        ),
                    )
                }

                HorizontalDivider(color = Divider, modifier = Modifier.padding(horizontal = 16.dp))

                // Privacy Policy
                SettingsRow(
                    icon = Icons.Outlined.Policy,
                    label = stringResource(R.string.settings_screen_privacy_policy_label),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.customer_support_link)))
                        context.startActivity(intent)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // About section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = MutedText,
                    )
                    Text(
                        text = stringResource(R.string.settings_screen_about_label),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Accent,
                        letterSpacing = 1.5.sp,
                        modifier = Modifier.padding(start = 12.dp),
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.settings_screen_about_text),
                    fontSize = 14.sp,
                    color = MutedText,
                    lineHeight = 22.sp,
                )
            }
        }
    }
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = MutedText,
        )
        Text(
            text = label,
            fontSize = 15.sp,
            color = OnSurface,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
        )
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Divider,
        )
    }
}
