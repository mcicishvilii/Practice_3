package com.example.practice_3.ui.list

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice_3.ui.MoviesAdapter
import com.example.practice_3.comon.bases.BaseFragment
import com.example.practice_3.databinding.FragmentMainBinding
import com.example.practice_3.domain.model.MoviesDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }
    private val vm: MainViewModel by viewModels()

    override fun viewCreated() {
        observe()

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                findMovies(newText)
                return true
            }
        })
    }

    override fun listeners() {
        gotoDetails()
        removeAll()
    }

    private fun observe() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.getMovies()
                vm.stateMovies.collectLatest {
                    moviesAdapter.submitList(it)
                }
            }
        }
    }

    private fun gotoDetails() {
        moviesAdapter.apply {
            moviesAdapter.apply {
                setOnItemClickListener { movie, i ->
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToDetailsFragment(
                            MoviesDomain(
                                movie.adult,
                                movie.id,
                                movie.backdropPath,
                                movie.originalTitle,
                                movie.posterPath,
                                movie.releaseDate,
                                movie.title,
                                movie.overview,
                                movie.voteAverage,
                                movie.voteCount,
                            )
                        )
                    )
                }
            }
        }
    }

    private fun removeAll() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.tvTitle.setOnClickListener {
                    vm.removeAll()
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun findMovies(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.stateMovies.collectLatest {
                    val filteredMovies = it.filter {
                        it.title!!.contains(query, true)
                    }
                    moviesAdapter.submitList(filteredMovies)
                }
            }
        }
    }


}
