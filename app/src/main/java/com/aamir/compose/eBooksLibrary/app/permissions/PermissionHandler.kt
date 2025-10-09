package com.aamir.compose.eBooksLibrary.app.permissions

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun PermissionHandler(
    appPermission: AppPermission,
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) onGranted() else onDenied()
    }

    LaunchedEffect(Unit) {
        val allGranted = appPermission.permissions.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }

        val isStorage = appPermission == AppPermission.Storage
        val isPhotoPickerSupported = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

        when {
            allGranted -> onGranted()
            isStorage && isPhotoPickerSupported -> onGranted() // no storage permission needed 14+
            else -> permissionLauncher.launch(appPermission.permissions)
        }
    }
}
