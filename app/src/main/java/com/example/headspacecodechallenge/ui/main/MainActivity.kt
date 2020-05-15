package com.example.headspacecodechallenge.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.headspacecodechallenge.R
import com.example.headspacecodechallenge.db.ImageDatabase
import com.example.headspacecodechallenge.db.entites.ImageEntry
import com.example.headspacecodechallenge.network.WebService
import com.example.headspacecodechallenge.repository.ImageRepositoryImpl
import com.example.headspacecodechallenge.ui.adapter.ImageAdapter
import com.example.headspacecodechallenge.viewmodel.MainViewModel
import com.example.headspacecodechallenge.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var imageAdapter: ImageAdapter
    private var imageDatabase: ImageDatabase? = null
    private var pageCounter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        createDBInstance()

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ImageRepositoryImpl(WebService.instance, imageDatabase))
        ).get(MainViewModel::class.java)

        viewModel.state.observe(this, Observer { appState ->
            when (appState) {
                is MainViewModel.AppState.SUCCESS -> displayImages(appState.imageList)
                is MainViewModel.AppState.ERROR -> displayMessage(appState.message)
                is MainViewModel.AppState.EMPTY -> displayEmpty()
                is MainViewModel.AppState.LOADING -> displayLoading()
            }
        })

        if (!viewModel.loaded) {
            viewModel.getImages(pageCounter)
        }
    }

    private fun displayLoading() {
        progressBar.visibility = View.VISIBLE
        rvImages.visibility = View.GONE
        containerMessage.visibility = View.GONE
    }

    private fun displayEmpty() {
        imageAdapter.images.clear()
        imageAdapter.notifyDataSetChanged()

        progressBar.visibility = View.GONE
        rvImages.visibility = View.VISIBLE
        containerMessage.visibility = View.GONE

        if (imageAdapter.itemCount > 0) {
            Toast.makeText(this, "No New Items Were Added", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(this, "There are no New Items to add", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun displayMessage(message: String) {

        progressBar.visibility = View.GONE
        rvImages.visibility = View.GONE
        containerMessage.visibility = View.VISIBLE

        messageText.text = message
    }

    private fun displayImages(images: List<ImageEntry>) {

        imageAdapter.images.clear()
        imageAdapter.images.addAll(images)
        imageAdapter.notifyDataSetChanged()

        progressBar.visibility = View.GONE
        rvImages.visibility = View.VISIBLE
        containerMessage.visibility = View.GONE

        pageCounter++
    }

    private fun createDBInstance() {
        imageDatabase = ImageDatabase.getInstance(application)
    }

    private fun initRecyclerView() {
        rvImages.layoutManager = GridLayoutManager(this, 2)
        imageAdapter = ImageAdapter(mutableListOf())
        rvImages.adapter = imageAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.getImages(pageCounter)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
