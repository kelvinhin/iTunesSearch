package com.kelvinhin.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kelvinhin.itunessearch.adapter.parcelable
import com.kelvinhin.itunessearch.data.Results
import com.kelvinhin.itunessearch.databinding.ViewSongDetailPopUpBinding
import com.kelvinhin.itunessearch.model.SongDetailViewModel

class SongDetailPopUp : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "SongDetailPopUp"
        const val DETAIL_DATA = "SongDetailData"
    }

    private lateinit var songDetailViewModel: SongDetailViewModel
    private lateinit var binding: ViewSongDetailPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewSongDetailPopUpBinding.inflate(inflater)
        songDetailViewModel = ViewModelProvider(this)[SongDetailViewModel::class.java]
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewHolder = songDetailViewModel



        arguments?.parcelable<Results>(DETAIL_DATA)?.let { result ->
            binding.result = result
            songDetailViewModel.checkIsFavorite(this.requireContext(), result.collectionId)
            binding.executePendingBindings()

            binding.imgFavorite.setOnClickListener {
                songDetailViewModel.clickFavoriteState(result)
            }
        }

        return binding.root
    }
}