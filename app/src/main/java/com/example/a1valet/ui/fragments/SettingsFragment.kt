package com.example.a1valet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a1valet.R
import com.example.a1valet.databinding.FragmentSettingsBinding
import com.example.a1valet.ui.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnResetFav.setOnClickListener {
            viewModel.resetFavDevices()
            Toast.makeText(context, getString(R.string.fav_clear_msg), Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnExit.setOnClickListener {
            activity?.finishAffinity()
        }
    }
}