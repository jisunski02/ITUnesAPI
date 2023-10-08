package com.apple.itunesapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.apple.itunesapi.databinding.FragmentMovieDetailsBinding
import com.apple.itunesapi.presentation.viewmodel.MoviesViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding;
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        val args : MovieDetailsFragmentArgs by navArgs()
        val movies = args.selectedMovie

        binding.trackName.text = movies.trackName
        binding.genre.text = movies.primaryGenreName
        binding.trackPrice.text = movies.trackPrice.toString()

        Glide.with(binding.movieImage.context)
            .load(movies.artworkUrl100)
            .into(binding.movieImage)

        binding.addToFavorites.setOnClickListener {

            try{
                viewModel.saveFavoriteMovie(movies)
                Snackbar.make(view,"Saved to Favorites",Snackbar.LENGTH_LONG).show()
            }
              catch (e:Exception){
                  Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG).show()
              }
        }

    }
}