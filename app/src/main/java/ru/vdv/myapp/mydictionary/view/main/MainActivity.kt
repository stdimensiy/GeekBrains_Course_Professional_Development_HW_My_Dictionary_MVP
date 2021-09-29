package ru.vdv.myapp.mydictionary.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vdv.myapp.mydictionary.R
import ru.vdv.myapp.mydictionary.databinding.ActivityMainBinding
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.presenter.DataPresenterRU
import ru.vdv.myapp.mydictionary.view.common.BaseActivity
import ru.vdv.myapp.mydictionary.view.common.OnListItemClickListener
import ru.vdv.myapp.mydictionary.view.common.OnSearchClickListener

class MainActivity : BaseActivity<AppState>() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null
    private val observer = Observer<AppState> { renderData(it) }

    private val onListItemClickListener: OnListItemClickListener =
        object : OnListItemClickListener {
            override fun onItemClick(data: DataPresenterRU) {
                Toast.makeText(
                    this@MainActivity, data.word,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override val model: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getData(searchWord).observe(this@MainActivity, observer)
                }
            })
            searchDialogFragment.show(
                supportFragmentManager,
                SearchDialogFragment.TAG
            )
        }
        if (savedInstanceState != null) {
            model.getData().observe(this@MainActivity, observer)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData("Привет!").observe(this, observer)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }
}