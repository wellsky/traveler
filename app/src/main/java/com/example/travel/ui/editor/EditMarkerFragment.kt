package com.example.travel.ui.editor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import club.electro.util.AndroidUtils
import com.example.travel.R
import com.example.travel.databinding.FragmentEditMarkerBinding
import com.example.travel.dto.MapMarker
import com.example.travel.ui.map.MapViewModel
import com.example.travel.utils.DoubleArg
import com.example.travel.utils.LongArg
import com.google.android.material.snackbar.Snackbar


class EditMarkerFragment : Fragment() {
    companion object {
        var Bundle.markerId: Long by LongArg
        var Bundle.newMarkerLat: Double by DoubleArg
        var Bundle.newMarkerLng: Double by DoubleArg
    }

    private val viewModel: EditMarkerViewModel by viewModels (
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditMarkerBinding.inflate(inflater, container, false)

        arguments?.markerId?.let { id ->
            if (id != 0L) {
                viewModel.loadCurrentMarker(id)
            }
        }

        arguments?.newMarkerLat?.let { lat ->
            arguments?.newMarkerLng?.let { lng ->
                viewModel.setCurrentMarker(
                    MapMarker(
                        lat = lat,
                        lng = lng
                    )
                )
            }
        }

        viewModel.currentMarker.observe(viewLifecycleOwner) { marker ->
            with (binding) {
                name.setText(marker.name)
                text.setText(marker.text)
                coords.text = getString(R.string.coords, marker.lat, marker.lng)
            }
        }


        binding.saveMarker.setOnClickListener {
            viewModel.currentMarker.value?.let {    current ->
                viewModel.setCurrentMarker(
                    MapMarker(
                        id = current.id,
                        lat = current.lat,
                        lng = current.lng,
                        name = binding.name.text.toString().trim(),
                        text = binding.text.text.toString().trim()
                    )
                )

                viewModel.saveCurrentMarker()

                AndroidUtils.hideKeyboard(requireView())
                Snackbar.make(binding.root, R.string.marker_saved, Snackbar.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}