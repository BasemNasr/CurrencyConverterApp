package com.currency.converter.presentation.details


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.currency.converter.R
import com.currency.converter.base.ParentFragment
import com.currency.converter.data.models.response.CurrenciesData
import com.currency.converter.data.network.remote.NetworkResponse
import com.currency.converter.databinding.FragmentDetailsBinding
import com.currency.converter.presentation.MainNavigationController
import com.currency.converter.presentation.details.adapter.HistoricalExchangeAdapter
import com.currency.converter.presentation.details.adapter.OtherCurrenciesAdapter
import com.currency.converter.utils.AppUtils
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class DetailsFragment : ParentFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDetailsBinding::inflate

    private val mNavigator: MainNavigationController by lazy {
        MainNavigationController(
            findNavController()
        )
    }

    private val viewModel: DetailsViewModel by viewModels()
    private val currenciesArray by lazy { resources.getStringArray(R.array.currencies) }
    private val args: DetailsFragmentArgs by navArgs()
    private val historicalAdapter by lazy { HistoricalExchangeAdapter() }
    private val otherCurrenciesAdapter by lazy { OtherCurrenciesAdapter() }


    lateinit var barDataSet1: BarDataSet
    lateinit var barDataSet2: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>

    @SuppressLint("SetTextI18n")
    override fun initializeComponents(view: View) {
        setupRecycler()
        observeErrorLoading()
        observeHistoricalData()
        observeOtherCurrencies()
        binding.apply {
            tvDetailsTitle.text = getString(R.string.historical_data_from_to, args.from, args.to)
            tvOtherFrom.text = "1 ${args.from} ${getString(R.string.to)}"
        }
        val currentDate = AppUtils.gettingCurrentData()
        val fromDate = AppUtils.gettingDateBefore3Days(currentDate)
        val middleDate = AppUtils.gettingDateBefore2Days(currentDate)
        val toDate = AppUtils.gettingYesterdayDate(currentDate)
        viewModel.getHistoricalCurrencies(args.from, "$fromDate", "$toDate", "$middleDate")
        viewModel.getCurrencies(args.from)
    }

    private fun observeHistoricalData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.historicalList.collectLatest {
                it?.let {
                    showLoading(false)
                    if(it.isNotEmpty()){
                        historicalAdapter.submitList(it, args.from, args.to)
                        setUpChartResult(it)
                    }
                }
            }
        }
    }
    private fun observeOtherCurrencies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.otherCurrencies.collectLatest {
                it?.let {
                    showLoading(false)
                    if(it.isNotEmpty()){
                        otherCurrenciesAdapter.submitList(it)
                    }
                }
            }
        }
    }



    private fun setUpChartResult(data: ArrayList<CurrenciesData>) {
        barDataSet1 = BarDataSet(getBarChartDataForSet1(data), "${args.from}")
        barDataSet1.color = ContextCompat.getColor(requireContext(), R.color.purple_200)
        barDataSet2 = BarDataSet(getBarChartDataForSet2(data), "${args.to}")
        barDataSet2.color = ContextCompat.getColor(requireContext(), R.color.teal_200)
        var days = ArrayList<String>()

        for (date in data) {
            days.add(date.date ?: "NA")
        }
        // on below line we are adding bar data set to bar data
        var data = BarData(barDataSet1, barDataSet2)

        // on below line we are setting data to our chart
        binding.chart.data = data

        // on below line we are setting description enabled.
        binding.chart.description.isEnabled = false

        var xAxis = binding.chart.xAxis

        xAxis.valueFormatter = IndexAxisValueFormatter(days)

        xAxis.setCenterAxisLabels(true)

        xAxis.position = XAxis.XAxisPosition.BOTTOM

        xAxis.granularity = 1f

        xAxis.isGranularityEnabled = true

        binding.chart.isDragEnabled = true

        binding.chart.setVisibleXRangeMaximum(3f)

        val barSpace = 0.1f

        val groupSpace = 0.5f

        data.barWidth = 0.15f

        binding.chart.xAxis.axisMinimum = 0f

        binding.chart.animate()
        binding.chart.groupBars(0f, groupSpace, barSpace)
        binding.chart.invalidate()

    }


    private fun getBarChartDataForSet1(data: ArrayList<CurrenciesData>): ArrayList<BarEntry> {
        barEntriesList = ArrayList()
        data.forEachIndexed { index, currency ->
            barEntriesList.add(
                BarEntry(
                    (index+1).toFloat(),
                    AppUtils.getCurrencyValue("${args.from}", currency)?.toFloat()?:0f
                )
            )
        }
        return barEntriesList
    }

    private fun getBarChartDataForSet2(data: ArrayList<CurrenciesData>): ArrayList<BarEntry> {
        barEntriesList = ArrayList()
        data.forEachIndexed { index, currency ->
            barEntriesList.add(
                BarEntry(
                    (index + 1).toFloat(),
                    AppUtils.getCurrencyValue("${args.to}", currency)?.toFloat()?:0f
                )
            )
        }
        return barEntriesList
    }


    private fun setupRecycler() {
        binding.rvHistoricalData.apply {
            adapter = historicalAdapter
            layoutManager = LinearLayoutManager(
                this@DetailsFragment.requireContext(), LinearLayoutManager.VERTICAL, false
            )
        }
        binding.rvOther.apply {
            adapter = otherCurrenciesAdapter
            layoutManager = LinearLayoutManager(
                this@DetailsFragment.requireContext(), LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun observeErrorLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorLoadingState.collectLatest {
                when (it) {
                    NetworkResponse.Loading -> {
                        showLoading(true)
                    }

                    is NetworkResponse.ApiError -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "${getString(R.string.api_error_occured)}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is NetworkResponse.NetworkError -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "${getString(R.string.no_internet_connection)}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "${getString(R.string.something_wrong_happen)}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


}