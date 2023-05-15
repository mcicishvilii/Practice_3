package com.example.practice_3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_3.databinding.SingleMovieLayoutBinding
import com.example.practice_3.domain.model.MoviesDomain

class MoviesAdapter :
    ListAdapter<MoviesDomain, MoviesAdapter.MoviesViewHolder>(
        MoviesDiffCallBack()
    ) {

    private lateinit var itemClickListener: (MoviesDomain, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): MoviesViewHolder {
        val binding =
            SingleMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindData()
    }

    inner class MoviesViewHolder(private val binding: SingleMovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: MoviesDomain? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvMovieName.text = model?.originalTitle.toString()
                tvAverageRating.text = "Average votes: ${model?.voteAverage.toString()}"
                tvRating.text = "Total votes: ${model?.voteCount.toString()}"
                tvReleaseDate.text = "Release date: ${model?.releaseDate.toString()}"
                if (model?.adult!!) {
                    tvGenre.text = "Adult +18"
                } else {
                    tvGenre.text = "Not Adult"
                }

                Glide.with(this.ivImagePoster)
                    .load("https://image.tmdb.org/t/p/w500${model?.posterPath}")
                    .into(ivImagePoster)
            }
            binding.ivImagePoster.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (MoviesDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }
}

class MoviesDiffCallBack :
    DiffUtil.ItemCallback<MoviesDomain>() {
    override fun areItemsTheSame(
        oldItem: MoviesDomain,
        newItem: MoviesDomain,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MoviesDomain,
        newItem: MoviesDomain,
    ): Boolean {
        return oldItem == newItem
    }


}