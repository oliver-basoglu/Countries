package com.oliver.countries.ui.countries.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oliver.countries.R
import com.oliver.countries.data.model.CountriesResponseResult
import com.oliver.countries.data.model.Country
import com.oliver.countries.databinding.FragmentCountriesBinding
import com.oliver.countries.ui.countries.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CountriesFragment : Fragment(R.layout.fragment_countries) {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private val countriesViewModel: CountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        countriesViewModel.fetchCountriesData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesViewModel.countriesResponseResultLiveData.observe(
            viewLifecycleOwner
        ) { countriesResponseResult ->
            countriesResponseResult?.let { it ->
                when (it) {
                    is CountriesResponseResult.Success -> {
                        buildCountryRecycler(it.countries)
                    }
                    is CountriesResponseResult.Failure -> {
                        Timber.d(it.error.localizedMessage)
                        Toast.makeText(
                            context,
                            getString(R.string.api_loading_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is CountriesResponseResult.IsLoading -> {
                        Toast.makeText(
                            context,
                            getString(R.string.data_loading_message),
                            LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun buildCountryRecycler(countryList: List<Country>) {
        val countriesAdapter = CountriesAdapter(countryList)
        countriesAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.name, LENGTH_SHORT).show()
        }

        binding.recyclerCountries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}