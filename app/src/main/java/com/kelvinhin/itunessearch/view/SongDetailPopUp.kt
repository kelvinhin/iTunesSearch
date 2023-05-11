package com.kelvinhin.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kelvinhin.itunessearch.adapter.parcelable
import com.kelvinhin.itunessearch.data.Results
import com.kelvinhin.itunessearch.databinding.ViewSongDetailPopUpBinding

class SongDetailPopUp : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "SongDetailPopUp"
        const val DETAIL_DATA = "SongDetailData"
    }

    private lateinit var binding: ViewSongDetailPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewSongDetailPopUpBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        arguments?.parcelable<Results>(DETAIL_DATA).let { result ->
            binding.result = result
            binding.executePendingBindings()
        }
        return binding.root
    }
}