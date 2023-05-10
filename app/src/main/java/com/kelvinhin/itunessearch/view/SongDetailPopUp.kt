package com.kelvinhin.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kelvinhin.itunessearch.databinding.ViewSongDetailPopUpBinding

class SongDetailPopUp : BottomSheetDialogFragment() {
    private lateinit var binding: ViewSongDetailPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewSongDetailPopUpBinding.inflate(inflater)
        return binding.root
    }
}