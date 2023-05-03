package com.kelvinhin.itunessearch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kelvinhin.itunessearch.adapter.SongItemAdapter
import com.kelvinhin.itunessearch.constants.Constants
import com.kelvinhin.itunessearch.data.SearchRequest
import com.kelvinhin.itunessearch.databinding.ActivityMainBinding
import com.kelvinhin.itunessearch.model.SearchViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        binding.viewModel = searchViewModel
        binding.searchResultRecycler.adapter = SongItemAdapter()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        binding.searchView.editText.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    Log.d(Constants.LOG_TAG, "search item: " + textView.text)
                    binding.searchBar.text = textView.text
                    binding.searchView.hide()
                    searchViewModel.doSearch(
                        SearchRequest(
                            term = textView.text.toString(),
                            country = searchViewModel.getCountry().value ?: "hk"
                        )
                    )
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_country_picker -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}