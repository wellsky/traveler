package com.example.travel.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.travel.adapter.MarkersListAdapter
import com.example.travel.adapter.OnInteractionListener
import com.example.travel.databinding.FragmentEditMarkerBinding
import com.example.travel.databinding.FragmentListBinding
import com.example.travel.dto.MapMarker
import com.example.travel.ui.map.MapViewModel

class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels (
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = MarkersListAdapter(object : OnInteractionListener {
            override fun onClick(marker: MapMarker) {

            }
        })

        binding.markersList.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, { items ->
            adapter.submitList(items)
        })


        return binding.root
    }
}