package com.kelvinhin.itunessearch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import com.kelvinhin.itunessearch.adapter.SongItemAdapter
import com.kelvinhin.itunessearch.adapter.setCountryValue
import com.kelvinhin.itunessearch.constants.Constants
import com.kelvinhin.itunessearch.databinding.ActivityMainBinding
import com.kelvinhin.itunessearch.databinding.ViewSelectCountryBinding
import com.kelvinhin.itunessearch.model.PageDirection
import com.kelvinhin.itunessearch.model.SearchViewModel
import com.kelvinhin.itunessearch.view.SongDetailPopUp

class MainActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var menuBinding: ViewSelectCountryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.lifecycleOwner = this
        binding.viewModel = searchViewModel
        binding.searchResultRecycler.adapter = SongItemAdapter { selectedResult ->
            val songDetail = SongDetailPopUp()
            songDetail.arguments = Bundle().apply {
                putParcelable(SongDetailPopUp.DETAIL_DATA, selectedResult)
            }
            songDetail.show(supportFragmentManager, SongDetailPopUp.TAG)
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        binding.searchView.editText.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    binding.searchBar.text = textView.text
                    binding.searchView.hide()
                    searchViewModel.constructInitSearchRequest(
                        keyword = textView.text.toString(),
                        selectedEntityId = binding.searchViewOptions.chipGroupFilter.checkedChipId
                    )
                    searchViewModel.doSearch()
                    true
                }
                else -> false
            }
        }

        binding.btnPrevious.setOnClickListener {
            Log.d(Constants.LOG_TAG, "Original page number: ${searchViewModel.pageNumber.value}")
            searchViewModel.constructJumpPageSearchRequest(PageDirection.PREVIOUS)
            Log.d(Constants.LOG_TAG, "New page number: ${searchViewModel.pageNumber.value}")
            searchViewModel.doSearch()
        }

        binding.btnNext.setOnClickListener {
            Log.d(Constants.LOG_TAG, "Original page number: ${searchViewModel.pageNumber.value}")
            searchViewModel.constructJumpPageSearchRequest(PageDirection.NEXT)
            Log.d(Constants.LOG_TAG, "New page number: ${searchViewModel.pageNumber.value}")
            searchViewModel.doSearch()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        with(menu) {
            findItem(R.id.action_country_picker)?.actionView?.let {
                menuBinding = ViewSelectCountryBinding.bind(it)
            }
        }
        menuBinding.countryPicker.setCountryValue(searchViewModel)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_country_picker -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}