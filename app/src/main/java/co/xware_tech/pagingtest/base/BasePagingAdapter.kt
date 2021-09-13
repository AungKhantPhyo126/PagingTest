package co.xware_tech.windiesel.base

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BasePagingAdapter<D : Any, VH : BaseViewHolder<D>>(
    diffCallback: DiffUtil.ItemCallback<D>,
    val onClick: OnClick<D> = { _, _, _ -> }
) : PagingDataAdapter<D, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.setData(it) }?: holder.bindPlaceHolder()
    }

    abstract fun getItemLayoutId(): Int

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
}

