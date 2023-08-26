package com.currency.converter.presentation.home


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.currency.converter.R
import com.currency.converter.base.ParentFragment
import com.currency.converter.data.network.remote.NetworkResponse
import com.currency.converter.databinding.FragmentHomeBinding
import com.currency.converter.presentation.MainNavigationController
import com.currency.converter.utils.NumberUtils.roundTo2DecimalAsString
import com.currency.converter.utils.rotateImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class HomeFragment : ParentFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    private val mNavigator: MainNavigationController by lazy {
        MainNavigationController(
            findNavController()
        )
    }

    private val viewModel: HomeViewModel by viewModels()
    private val currenciesArray by lazy { resources.getStringArray(R.array.currencies) }
    //0=from,1=to
    private var currentFocused = 0
    @SuppressLint("SetTextI18n")
    override fun initializeComponents(view: View) {
        setUpViewActions()
        observeCurrencies()
        observeErrorLoading()
        viewModel.getCurrencies()
    }

    private fun observeErrorLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorLoadingState.collectLatest {
                when (it) {
                    NetworkResponse.Loading -> {
                        showLoading(true)
                    }
                    else -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "${getString(R.string.connection_error)}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


    private fun observeCurrencies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currencies.collectLatest {
                it?.let {
                    showLoading(false)
                    updateConvertedToValue()
                }
            }
        }
    }

    private fun updateConvertedToValue() {
        binding.etTo.setText(
            roundTo2DecimalAsString(
                viewModel.gettingCurrentToValue(
                    binding.etFrom.text.toString().toDouble(),
                )
            )
        )
    }
    private fun updateConvertedFromValue() {
        binding.etFrom.setText(
            roundTo2DecimalAsString(
                viewModel.gettingCurrentFromValue(
                    binding.etTo.text.toString().toDouble(),
                )
            )
        )
    }

    private fun setUpViewActions() {
        binding.apply {
            ivSwap.setOnClickListener {
                ivSwap.rotateImage()
                var oldFromPosition = spinnerFrom.selectedItemPosition
                spinnerFrom.setSelection(spinnerTo.selectedItemPosition)
                spinnerTo.setSelection(oldFromPosition)
            }

            btnShowMore.setOnClickListener {

            }

            spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    p3: Long
                ) {
                    viewModel.updateSelectedBase(currenciesArray[position])
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
            spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    p3: Long
                ) {
                    viewModel.updateSelectedTo(currenciesArray[position])
                    updateConvertedToValue()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            etFrom.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    currentFocused = 0
                }
            }
            etTo.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    currentFocused = 1
                }
            }
            etFrom.doAfterTextChanged {
                if(currentFocused==0){
                    if (it?.isNotEmpty() == true)
                        updateConvertedToValue()
                    else {
                        etFrom.setText("1")
                    }
                }
            }
            etTo.doAfterTextChanged {
                if(currentFocused==1){
                    if (it?.isNotEmpty() == true)
                        updateConvertedFromValue()
                    else {
                        etTo.setText("1")
                    }
                }
            }
        }
    }


    private fun showLoading(show: Boolean) {
        if (show) {
            binding.btnShowMore.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnShowMore.visibility = View.VISIBLE
        }
    }
}