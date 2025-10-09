package com.aamir.compose.eBooksLibrary.app.permissions

import android.Manifest
import android.os.Build

enum class AppPermission(
    val permissions: Array<String>,
    val rationale: String
) {
    Location(
        permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        rationale = "Location permission is needed to show your current position on the map."
    ),

    Storage(
        permissions = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES
            )

            else -> arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        },
        rationale = "Storage permission is needed to select and save photos."
    )
}