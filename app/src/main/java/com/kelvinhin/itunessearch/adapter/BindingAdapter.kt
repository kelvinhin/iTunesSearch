package com.kelvinhin.itunessearch.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hbb20.CountryCodePicker
import com.kelvinhin.itunessearch.R
import com.kelvinhin.itunessearch.data.SearchResult

@BindingAdapter("recyclerData")
fun RecyclerView.bindRecyclerView(data: SearchResult?) {
    data?.let {
        this.adapter.apply {
            when (this) {
                is SongItemAdapter -> submitList(it.results)
            }
        }
    }
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        this.load(imgUri) {
            placeholder(R.drawable.ic_downloading_24)
            error(R.drawable.ic_broken_image_24)
        }
    }
}

@BindingAdapter("countryValue")
fun CountryCodePicker.setCountryValue(country: MutableLiveData<String>) {
    this.setOnCountryChangeListener { country.postValue(this.selectedCountryNameCode) }
}