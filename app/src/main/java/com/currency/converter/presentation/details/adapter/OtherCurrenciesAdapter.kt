package com.currency.converter.presentation.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.currency.converter.base.ViewBindingVH
import com.currency.converter.data.models.CurrencyModel
import com.currency.converter.databinding.OtherCurrenciesItemBinding
import com.currency.converter.utils.NumberUtils
import com.currency.converter.utils.diffutils.DiffUtilCallBack
import com.currency.converter.utils.diffutils.ItemsDiffCallback

class OtherCurrenciesAdapter : RecyclerView.Adapter<ViewBindingVH<OtherCurrenciesItemBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingVH<OtherCurrenciesItemBinding> =
        ViewBindingVH.create(parent, OtherCurrenciesItemBinding::inflate)

    override fun onBindViewHolder(
        holder: ViewBindingVH<OtherCurrenciesItemBinding>,
        position: Int
    ) {
        val data = mNewList[position]
        holder.binding.apply {
            tvCurrencyTitle.text = data.title
            tvCurrencyValue.text = NumberUtils.roundTo2DecimalAsString(data.value ?: 0.0)
        }
    }

    private val callback by lazy { DiffUtilCallBack(this) }
    private val mNewList by lazy { arrayListOf<CurrencyModel>() }

    fun submitList(list: List<CurrencyModel>) {
        updateListItems(list)
    }

    private fun updateListItems(list: List<CurrencyModel>) {
        val diffCallback = ItemsDiffCallback(mNewList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mNewList.clear()
        mNewList.addAll(list)
        diffResult.dispatchUpdatesTo(callback)
    }


    override fun getItemCount() = mNewList.size
}