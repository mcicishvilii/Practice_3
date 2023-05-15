package com.example.practice_3.ui.details

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practice_3.comon.bases.BaseFragment
import com.example.practice_3.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    val args: DetailsFragmentArgs by navArgs()

    override fun viewCreated() {

        val movie = args.movieInfo
        binding.apply {
            tvMovieName.text = movie.title
            tvAboutMovie.text = movie.overview
            tvRateCountDetails.text = "Total votes: ${movie.voteCount.toString()}"
            tvAverageRatingDetails.text = "Votes: ${movie.voteAverage.toString()}"
            tvReleaseDateDetails.text = "Release date: ${movie.releaseDate}"

            Glide.with(this.ivLargePoster)
                .load("https://image.tmdb.org/t/p/w500${movie?.backdropPath}")
                .into(ivLargePoster)

            Glide.with(this.ivSmallPoster)
                .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
                .into(ivSmallPoster)
        }
    }

    override fun listeners() {

    }


}