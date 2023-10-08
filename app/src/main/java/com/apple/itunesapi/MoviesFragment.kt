package com.apple.itunesapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apple.itunesapi.data.util.Resource
import com.apple.itunesapi.databinding.FragmentMoviesBinding
import com.apple.itunesapi.presentation.adapter.MoviesAdapter
import com.apple.itunesapi.presentation.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private var term = ""
    private var media = "movie"
    private var limit = "200"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        moviesAdapter = (activity as MainActivity).moviesAdapter

        moviesAdapter.setOnItemClickLister {
            val bundle = Bundle().apply {
                putSerializable("selected_movie", it)
            }

            findNavController().navigate(
                R.id.action_moviesFragment_to_movieDetailsFragment, bundle
            )
        }

        moviesAdapter.hideFavorite(false)

        moviesAdapter.setOnHeartClickLister {
            viewModel.saveFavoriteMovie(it)
            Snackbar.make(view,"Saved to Favorites", Snackbar.LENGTH_LONG).show()
        }

        initRecyclerview()
        viewMoviesList()
        setSearchMovies()

    }

    private fun initRecyclerview() {
        //moviesAdapter = MoviesAdapter()

        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        //Java way
//        binding.rvMovies.adapter = moviesAdapter
//        binding.rvMovies.layoutManager = LinearLayoutManager(activity)
    }

    private fun viewMoviesList(){
        viewModel.getMovies(term, media, limit)
        viewModel.movieList.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Loading -> {
                    showProgressbar()
                }

                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        moviesAdapter.differ.submitList(it.movies.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured : $it", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setSearchMovies(){

        moviesAdapter.differ.submitList(null)
        moviesAdapter.differ.currentList.clear()

        binding.searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchMovies(query.toString(), "movie", "200")
                viewSearchedMovieList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(50)
                    viewModel.searchMovies(newText.toString(), "movie", "200")
                    viewSearchedMovieList()
                }

                return false
            }

        })

        binding.searchMovie.setOnCloseListener {
            initRecyclerview()
            viewMoviesList()
            false
        }

    }

    private fun viewSearchedMovieList(){

        viewModel.searchMovies.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Loading -> {
                    showProgressbar()
                }

                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        moviesAdapter.differ.submitList(it.movies.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured : $it", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    private fun showProgressbar(){
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressbar(){
        binding.progressbar.visibility = View.INVISIBLE
    }
}