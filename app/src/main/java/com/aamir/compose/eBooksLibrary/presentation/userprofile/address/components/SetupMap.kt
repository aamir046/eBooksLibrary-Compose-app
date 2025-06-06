package com.aamir.compose.eBooksLibrary.presentation.userprofile.address.components

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@SuppressLint("ClickableViewAccessibility")
fun setupMap(mapView: MapView, context: Context, onGeoPointSelected: (GeoPoint) -> Unit) {
    Configuration.getInstance().load(
        context,
        context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
    )

    mapView.setTileSource(TileSourceFactory.MAPNIK)
    mapView.setMultiTouchControls(true)
    mapView.controller.setZoom(15.0)

    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView).apply {
        enableMyLocation()
        enableFollowLocation()
        runOnFirstFix {
            myLocation?.let {
                val geoPoint = GeoPoint(it.latitude, it.longitude)
                mapView.controller.setCenter(geoPoint)
                onGeoPointSelected(geoPoint)
            }
        }
    }

    mapView.overlays.add(locationOverlay)

    mapView.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            val projection = mapView.projection
            val geoPoint = projection.fromPixels(event.x.toInt(), event.y.toInt())
            onGeoPointSelected(GeoPoint(geoPoint.latitude, geoPoint.longitude))
        }
        false
    }

}
