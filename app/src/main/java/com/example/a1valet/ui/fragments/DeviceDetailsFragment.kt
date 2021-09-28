package com.example.a1valet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a1valet.databinding.FragmentDeviceDetailsBinding
import com.example.a1valet.helper.Constants.Companion.SELECTED_DEVICE_ID_KEY
import com.example.a1valet.ui.viewmodels.DeviceDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDeviceDetailsBinding
    private val viewModel: DeviceDetailsViewModel by viewModels()
    private var selectedDeviceId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedDeviceId = it.getString(SELECTED_DEVICE_ID_KEY, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeviceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
    }

    private fun setObservers() {
        viewModel.getDeviceDetails(selectedDeviceId).observe(viewLifecycleOwner, {
            binding.device = it
        })

        binding.cbFav.setOnClickListener {
            viewModel.toggleFavourite(
                selectedDeviceId,
                binding.device?.isFav?.not() ?: false
            )
        }
    }
}