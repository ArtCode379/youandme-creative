package shop.youandmecreative.app.ui.composable.approot

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import shop.youandmecreative.app.ui.composable.navigation.NavRoute

data class BottomNavItem(
    @field:StringRes val titleRes: Int,
    @field:DrawableRes val iconRes: Int,
    val route: NavRoute,
)

@Composable
fun AppBottomBar(
    itemsInCart: Int,
    currentDestination: NavDestination?,
    navigationItems: List<BottomNavItem>,
    onNavigateToRoute: (BottomNavItem) -> Unit,
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = isSelectedDestination(currentDestination, item.route),
                onClick = { onNavigateToRoute(item) },
                icon = {
                    if (item.route == NavRoute.Cart) {
                        BadgedBox(
                            badge = {
                                if (itemsInCart > 0) {
                                    Badge {
                                        Text(itemsInCart.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(item.iconRes),
                                contentDescription = stringResource(item.titleRes),
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(item.iconRes),
                            contentDescription = stringResource(item.titleRes),
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                label = {
                    Text(text = stringResource(item.titleRes))
                },
            )
        }
    }
}

private fun isSelectedDestination(destination: NavDestination?, route: NavRoute): Boolean {
    return destination?.let {
        destination.hierarchy.any { navDestination -> navDestination.hasRoute(route::class) }
    } ?: return false
}