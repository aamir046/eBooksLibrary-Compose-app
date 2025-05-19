package com.aamir.compose.eBooksLibrary.presentation.main.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aamir.compose.eBooksLibrary.presentation.main.BottomNavItem
import com.aamir.compose.eBooksLibrary.presentation.theme.Gray
import com.aamir.compose.eBooksLibrary.presentation.theme.PurpleLight

@Composable
fun AppBottomBar(navController: NavHostController?=null){

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Categories,
        BottomNavItem.Authors,
        BottomNavItem.Profile
    )

    val currentRoute = navController?.currentBackStackEntryAsState()?.value?.destination?.route
    var lastClickTime = 0L
    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        containerColor = Gray,
    ){
        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier,
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        val now = System.currentTimeMillis()
                        if (now - lastClickTime > 1000) {
                            lastClickTime = now
                            navController?.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = PurpleLight,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun AppBottomBarPreview() {
    AppBottomBar()
}