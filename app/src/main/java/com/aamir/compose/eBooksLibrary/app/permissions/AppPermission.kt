package com.aamir.compose.eBooksLibrary.app.permissions

enum class AppPermission(
    val permissions: Array<String>,
    val rationale: String
) {
    Location(
        permissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        rationale = "Location permission is needed to show your current position on the map."
    )
}