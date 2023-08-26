package com.currency.converter.presentation.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.currency.converter.base.ViewBindingVH
import com.currency.converter.data.models.response.CurrenciesData
import com.currency.converter.databinding.RvHistoryDataItemBinding
import com.currency.converter.utils.AppUtils
import com.currency.converter.utils.NumberUtils
import com.currency.converter.utils.diffutils.DiffUtilCallBack
import com.currency.converter.utils.diffutils.ItemsDiffCallback

class HistoricalExchangeAdapter : RecyclerView.Adapter<ViewBindingVH<RvHistoryDataItemBinding>>() {

    var fromTitle = "USD"
    var toTitle = "USD"
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingVH<RvHistoryDataItemBinding> =
        ViewBindingVH.create(parent, RvHistoryDataItemBinding::inflate)

    override fun onBindViewHolder(
        holder: ViewBindingVH<RvHistoryDataItemBinding>,
        position: Int
    ) {
        val data = mNewList[position]
        holder.binding.apply {
            tvDate.text = "${data.date}"
            tvFromCurrencyTitle.text = fromTitle
            tvFromValue.text = "1"
            tvToCurrencyTitle.text = toTitle
            tvToValue.text = NumberUtils.roundTo2DecimalAsString(AppUtils.getCurrencyValue(toTitle, data)?:0.0)
        }
    }

    private val callback by lazy { DiffUtilCallBack(this) }
    private val mNewList by lazy { arrayListOf<CurrenciesData>() }

    fun submitList(list: List<CurrenciesData>, fromTitle: String, toTitle: String) {
        this.fromTitle = fromTitle
        this.toTitle = toTitle
        updateListItems(list)
    }

    private fun updateListItems(list: List<CurrenciesData>) {
        val diffCallback = ItemsDiffCallback(mNewList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mNewList.clear()
        mNewList.addAll(list)
        diffResult.dispatchUpdatesTo(callback)
    }


    override fun getItemCount() = mNewList.size
}