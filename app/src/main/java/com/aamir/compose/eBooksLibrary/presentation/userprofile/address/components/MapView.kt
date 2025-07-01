package com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
fun MapViewWithLocation(
    modifier: Modifier = Modifier,
    onLocationSelected: (GeoPoint) -> Unit,
    onMyLocationClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val mapViewRef = remember { mutableStateOf<MapView?>(null) }

    Box(modifier = modifier) {
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {
                    setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
                    setMultiTouchControls(true)
                    controller.setZoom(15.0)
                    zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

                    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this).apply {
                        enableMyLocation()
                        enableFollowLocation()
                        runOnFirstFix {
                            myLocation?.let {
                                val geoPoint = GeoPoint(it.latitude, it.longitude)
                                controller.setCenter(geoPoint)
                            }
                        }
                    }
                    overlays.add(locationOverlay)

                    val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                        override fun onLongPress(e: MotionEvent) {
                            val geoPoint = projection.fromPixels(e.x.toInt(), e.y.toInt())
                            onLocationSelected(GeoPoint(geoPoint.latitude, geoPoint.longitude))
                        }
                    })

                    setOnTouchListener { view, event ->
                        val handled = gestureDetector.onTouchEvent(event)

                        if (event.action == MotionEvent.ACTION_UP && !handled) {
                            view.performClick()
                        }

                        handled
                    }

                    mapViewRef.value = this
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )

        IconButton(
            onClick = {
                mapViewRef.value?.let { map ->
                    map.overlays
                        .filterIsInstance<MyLocationNewOverlay>()
                        .firstOrNull()
                        ?.myLocation
                        ?.let {
                            map.controller.animateTo(GeoPoint(it.latitude, it.longitude))
                            onMyLocationClick?.invoke()
                        }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(Icons.Default.MyLocation, contentDescription = "Locate Me")
        }
    }
}
