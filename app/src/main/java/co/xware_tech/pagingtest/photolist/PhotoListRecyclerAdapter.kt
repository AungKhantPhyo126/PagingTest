package co.xware_tech.pagingtest.photolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import co.xware_tech.pagingtest.R
import co.xware_tech.pagingtest.databinding.ItemRecyclerPhotoBinding
import co.xware_tech.pagingtest.domain.Photo
import co.xware_tech.pagingtest.loadImageUrl
import co.xware_tech.windiesel.base.BasePagingAdapter
import co.xware_tech.windiesel.base.BaseViewHolder

class PhotoListRecyclerAdapter() :
    BasePagingAdapter<Photo, PhotoViewHolder>(PhotoDiffUtil) {

    override fun getItemLayoutId(): Int = R.layout.item_recycler_photo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemRecyclerPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}

object PhotoDiffUtil : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}

class PhotoViewHolder(
    private val binding: ItemRecyclerPhotoBinding,
) : BaseViewHolder<Photo>(binding.root) {

    override fun bind(data: Photo) {
        loadImageUrl(binding.ivPhoto,data.download_url,binding.root.context)
        binding.tvAuthorName.text=data.author
    }

    override fun bindPlaceHolder() {

    }

}