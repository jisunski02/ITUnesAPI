package com.apple.itunesapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apple.itunesapi.databinding.FragmentFavoriteMoviesBinding
import com.apple.itunesapi.presentation.adapter.MoviesAdapter
import com.apple.itunesapi.presentation.viewmodel.MoviesViewModel


class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteMoviesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        moviesAdapter = (activity as MainActivity).moviesAdapter

        moviesAdapter.setOnItemClickLister {
            val bundle = Bundle().apply {
                putSerializable("selected_movie", it)
            }

            findNavController().navigate(
                R.id.action_favoriteMoviesFragment_to_movieDetailsFragment, bundle
            )
        }

        moviesAdapter.hideFavorite(true)

        initRecylerview()

        //display data in recyclerview
        viewModel.getSavedFavoriteMovie().observe(viewLifecycleOwner) {
            moviesAdapter.differ.submitList(it)
        }
    }

    private fun initRecylerview(){
        binding.rvSavedMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}