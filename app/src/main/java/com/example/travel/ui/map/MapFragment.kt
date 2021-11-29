package com.example.travel.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travel.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.navigation.fragment.findNavController
import com.example.travel.dto.MapMarker
import com.example.travel.ui.editor.EditMarkerFragment.Companion.markerId
import com.example.travel.ui.editor.EditMarkerFragment.Companion.newMarkerLat
import com.example.travel.ui.editor.EditMarkerFragment.Companion.newMarkerLng
import com.example.travel.utils.DoubleArg
import com.example.travel.utils.LongArg

class MapFragment : Fragment() {
    companion object {
        var Bundle.selectedMarkerId: Long by LongArg
    }

    private val viewModel: MapViewModel by viewModels (
        ownerProducer = ::requireParentFragment
    )

    private val callback = OnMapReadyCallback { googleMap ->
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        viewModel.data.observe(viewLifecycleOwner) { markersList ->
            markersList.forEach {
                val coords = LatLng(it.lat, it.lng)
                val marker = googleMap.addMarker(MarkerOptions().position(coords).title(it.name))
                marker?.tag = it

                arguments?.selectedMarkerId?.let { selected ->
                    if (selected == it.id) {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coords))
                    } else {
                        marker?.setVisible(false)
                    }
                }
            }
        }

        googleMap.setOnMapClickListener {
            findNavController().navigate(
                R.id.action_nav_map_to_editMarketFragment,
                Bundle().apply {
                    newMarkerLat = it.latitude
                    newMarkerLng = it.longitude
                }
            )
        }

        googleMap.setOnMarkerClickListener {
            val marker = it.tag as MapMarker
            findNavController().navigate(
                R.id.action_nav_map_to_editMarketFragment,
                Bundle().apply {
                    markerId = marker.id
                }
            )
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.setTitle("Map");

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}