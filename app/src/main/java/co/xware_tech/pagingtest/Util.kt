package co.xware_tech.pagingtest

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.airbnb.lottie.LottieComposition

import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.OnCompositionLoadedListener
import com.clzola.glottie.GlottieView
import com.clzola.glottie.GlottieViewTarget


fun loadImageUrl(imageView: GlottieView, imgUrl: String?,context: Context) {
    imgUrl?.let {
        Glide.with(imageView.context).load(it)
            .error(R.drawable.ic_broken_image)
            .into(GlottieViewTarget(imageView))
    }
}
//private fun createLottieDrawable(filename: String,context:Context): LottieDrawable? {
//    val lottieDrawable = LottieDrawable()
//    LottieComposition.Factory.fromAssetFileName(
//        context, filename
//    ) { composition: LottieComposition? ->
//        lottieDrawable.composition = composition
//        lottieDrawable.loop(true)
//        lottieDrawable.playAnimation()
//    }
//    return lottieDrawable
