package com.kelvinhin.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kelvinhin.itunessearch.databinding.ViewFavoriteListPopUpBinding

class FavoriteListPopUp : BottomSheetDialogFragment() {
    private lateinit var binding: ViewFavoriteListPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewFavoriteListPopUpBinding.inflate(inflater)
        return binding.root
    }
}