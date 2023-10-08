package com.apple.itunesapi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.databinding.MovieListLayoutBinding
import com.bumptech.glide.Glide

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Movies>(){
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.artworkUrl100 == newItem.artworkUrl100
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = differ.currentList[position]
        holder.bind(movies)
    }


    inner class MoviesViewHolder(
        val binding: MovieListLayoutBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(movies: Movies){
            binding.title.text = movies.trackName
            binding.genre.text = movies.primaryGenreName


            Glide.with(binding.movieImage.context)
                .load(movies.artworkUrl100)
                .into(binding.movieImage)

            if(onHideFavorite == true){
                binding.addToFavorites.visibility = View.GONE
                binding.tvFavorites.visibility = View.GONE
            }

            else{
                binding.addToFavorites.visibility = View.VISIBLE
                binding.tvFavorites.visibility = View.VISIBLE
            }

            binding.addToFavorites.setOnClickListener{
                onHeartClickListener?.let {
                    it(movies)
                }
            }

            binding.movieImage.setOnClickListener{
                onItemClickListener?.let {
                    it(movies)
                }
            }

        }
    }

    private var onItemClickListener: ((Movies)->Unit)?=null

    fun setOnItemClickLister(listener: ((Movies)->Unit)){
        onItemClickListener = listener
    }

    private var onHeartClickListener: ((Movies)->Unit)?=null

    fun setOnHeartClickLister(listener: ((Movies)->Unit)){
        onHeartClickListener = listener
    }


    private var onHideFavorite: Boolean?= null

    fun hideFavorite(isActivate: Boolean){
        onHideFavorite = isActivate
    }
}