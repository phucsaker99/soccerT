package com.example.soccert.ui.region

import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Country
import com.example.soccert.databinding.FragmentRegionBinding
import com.example.soccert.ui.adapter.CountryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegionFragment : BaseFragment<FragmentRegionBinding>() {
    override val layoutResource get() = R.layout.fragment_region
    override val viewModel by viewModel<RegionViewModel>()

    private val countryAdapter = CountryAdapter(this::onItemCountryClicked)

    override fun initViews() {

    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            regionVM = viewModel
        }
    }

    override fun initActions() {
        binding.cardPopularLeagues.setOnClickListener {
            val action =
                RegionFragmentDirections.actionRegionFragmentToHomeFragment("")
            findNavController().navigate(action)
        }
    }

    private fun onItemCountryClicked(country: Country) {
        val action = RegionFragmentDirections.actionRegionFragmentToHomeFragment(country.countryID)
        findNavController().navigate(action)
    }
}
