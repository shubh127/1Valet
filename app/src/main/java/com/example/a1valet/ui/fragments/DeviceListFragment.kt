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
import com.example.a1valet.databinding.FragmentDeviceListBinding
import com.example.a1valet.helper.Constants.Companion.SELECTED_DEVICE_ID_KEY
import com.example.a1valet.ui.adapters.DeviceListAdapter
import com.example.a1valet.ui.viewmodels.DeviceListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeviceListFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var deviceListAdapter: DeviceListAdapter
    private lateinit var binding: FragmentDeviceListBinding
    private val viewModel: DeviceListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentDeviceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDevicesInfo()
        applyObservers()
    }

    private fun applyObservers() {
        viewModel.allItemsFiltered.observe(viewLifecycleOwner) {
            setUpUI(it)
        }
    }

    private fun setUpUI(deviceList: List<Device>?) {
        if (deviceList.isNullOrEmpty()) {
            binding.tvErrorTxt.visibility = View.VISIBLE
            binding.rvDeviceList.visibility = View.GONE
        } else {
            binding.tvErrorTxt.visibility = View.GONE
            binding.rvDeviceList.visibility = View.VISIBLE
            binding.rvDeviceList.apply {
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
                putString(SELECTED_DEVICE_ID_KEY, selectedDeviceId)
            }
            Navigation.findNavController(binding.root)
                .navigate(
                    R.id.action_nav_device_list_to_deviceDetailsFragment,
                    bundle
                )
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as? SearchView
        searchView?.apply {
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