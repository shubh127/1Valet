package com.example.a1valet.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1valet.R
import com.example.a1valet.data.models.Device
import com.example.a1valet.databinding.FragmentFavDeviceBinding
import com.example.a1valet.helper.Constants
import com.example.a1valet.ui.adapters.DeviceListAdapter
import com.example.a1valet.ui.viewmodels.FavDeviceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavDeviceFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var deviceListAdapter: DeviceListAdapter
    private lateinit var binding: FragmentFavDeviceBinding
    private val viewModel: FavDeviceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentFavDeviceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyObservers()
    }

    private fun applyObservers() {
        viewModel.filteredFavDevices.observe(viewLifecycleOwner) {
            setUpUI(it)
        }
    }

    private fun setUpUI(deviceList: List<Device>) {
        if (deviceList.isNullOrEmpty()) {
            binding.rvDeviceList.visibility = View.GONE
            binding.tvErrorTxt.visibility = View.VISIBLE
        } else {
            binding.tvErrorTxt.visibility = View.GONE
            binding.rvDeviceList.apply {
                visibility = View.VISIBLE
                linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager
                deviceListAdapter = DeviceListAdapter(navigateToDetailPage())
                deviceListAdapter.submitList(deviceList)
                adapter = deviceListAdapter
            }
        }
    }

    private fun navigateToDetailPage() =
        { selectedDeviceId: String? ->
            val bundle = Bundle().apply {
                putString(Constants.SELECTED_DEVICE_ID_KEY, selectedDeviceId)
            }
            Navigation.findNavController(binding.root)
                .navigate(
                    R.id.action_nav_fav_device_list_to_deviceDetailsFragment,
                    bundle
                )
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        (menu.findItem(R.id.search).actionView as? SearchView)?.apply {
            queryHint = context.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    viewModel.setFilter(query ?: "")
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}