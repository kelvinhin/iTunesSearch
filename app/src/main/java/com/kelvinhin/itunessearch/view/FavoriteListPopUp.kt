package com.kelvinhin.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kelvinhin.itunessearch.adapter.SongItemAdapter
import com.kelvinhin.itunessearch.databinding.ViewFavoriteListPopUpBinding
import com.kelvinhin.itunessearch.model.FavoritesViewModel

class FavoriteListPopUp : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "FavoriteListPopUp"
    }

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: ViewFavoriteListPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewFavoriteListPopUpBinding.inflate(inflater)
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = favoritesViewModel

        binding.searchResultRecycler.adapter = SongItemAdapter { selectedResult ->
            val songDetail = SongDetailPopUp()
            songDetail.arguments = Bundle().apply {
                putParcelable(SongDetailPopUp.DETAIL_DATA, selectedResult)
            }
            songDetail.show(parentFragmentManager, SongDetailPopUp.TAG)
        }

        favoritesViewModel.getFavoritesList(requireContext())

        return binding.root
    }
}