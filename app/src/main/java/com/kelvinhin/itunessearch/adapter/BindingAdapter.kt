package com.kelvinhin.itunessearch.adapter

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hbb20.CountryCodePicker
import com.kelvinhin.itunessearch.R
import com.kelvinhin.itunessearch.constants.Constants
import com.kelvinhin.itunessearch.constants.Url
import com.kelvinhin.itunessearch.data.Results
import com.kelvinhin.itunessearch.model.ApiStatus
import com.kelvinhin.itunessearch.model.SearchViewModel

@BindingAdapter("recyclerData")
fun RecyclerView.bindRecyclerView(data: List<Results>?) {
    data?.let {
        this.adapter.apply {
            when (this) {
                is SongItemAdapter -> submitList(it) {
                    this@bindRecyclerView.smoothScrollToPosition(0)
                }
            }
        }
    }
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        this.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image_24)
        }
    }
}

@BindingAdapter("largeImageUrl")
fun ImageView.bindLargeImage(imgUrl: String?) {
    imgUrl?.let {
        val largeImgUrl = imgUrl.replace(Url.SMALL_IMG_PATH, Url.LARGE_IMG_PATH)
        val imgUri = largeImgUrl.toUri().buildUpon().scheme("https").build()
        this.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image_24)
        }
    }
}

@BindingAdapter("previousBtnVisibility")
fun Button.bindPreviousBtnVisbility(pageNumber: Int?) {
    pageNumber?.let {
        if (it == 0) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("nextBtnVisibility")
fun Button.bindNextBtnVisibility(resultCount: Int?) {
    resultCount?.let {
        if (it == Url.PAGE_SIZE) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}

fun CountryCodePicker.setCountryValue(viewModel: SearchViewModel) {
    this.setOnCountryChangeListener {
        viewModel.setCountry(this.selectedCountryNameCode)
        Log.d(Constants.LOG_TAG, "Selected country: ${this.selectedCountryNameCode}")
        viewModel.doSearch()
    }
}

@BindingAdapter("ApiStatus")
fun ImageView.bindApiStatus(status: ApiStatus?) {
    status?.let {
        when (it) {
            ApiStatus.LOADING -> {
                this.visibility = View.VISIBLE
                this.setImageResource(R.drawable.loading_animation)
            }
            ApiStatus.SUCCESS -> {
                this.visibility = View.GONE
            }
            ApiStatus.ERROR -> {
                this.visibility = View.VISIBLE
                this.setImageResource(R.drawable.ic_error_24)
            }
        }
    }
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT > Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
