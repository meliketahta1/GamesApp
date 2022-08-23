package com.melike.mobile_summer_intern.presentation.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.melike.mobile_summer_intern.databinding.CustomSearchToolBarBinding
import com.melike.mobile_summer_intern.databinding.FragmentGamesListBinding
import com.melike.mobile_summer_intern.presentation.model.GameResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GamesListFragment : Fragment() {

    private lateinit var binding: FragmentGamesListBinding
    private lateinit var toolbarBinding: CustomSearchToolBarBinding

    private lateinit var gameListPaginationAdapter: GameListPaginationAdapter

    private val gamesFeedViewModel: GamesFeedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesListBinding.inflate(layoutInflater)
        toolbarBinding = CustomSearchToolBarBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getGames()
        searchQueryListen()
    }

    private fun searchQueryListen() {
        toolbarBinding.svSearchGame.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchGames(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun initRecyclerView() {
        initAdapter()
        binding.rvGamesList.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = gameListPaginationAdapter
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(requireContext(), LinearLayoutManager.HORIZONTAL)
            )
        }
    }

    private fun initAdapter() {
        gameListPaginationAdapter = GameListPaginationAdapter(object : OnGameItemClicked {
            override fun onGameItemClicked(result: GameResult?) {
                result?.id?.let {
                    findNavController().navigate(
                        GamesListFragmentDirections.actionGamesListFragmentToGameDetailFragment(
                            result.id
                        )
                    )
                }
            }
        })
        observePagingAdapter()
    }

    private fun observePagingAdapter() {
        CoroutineScope(Dispatchers.Main).launch {
            gameListPaginationAdapter.loadStateFlow.map { it.refresh }
                .distinctUntilChanged()
                .collectLatest {
                    binding.bind(it)
                    if (it is LoadState.NotLoading) {
                        binding.tvNoGame.isVisible = gameListPaginationAdapter.itemCount < 1
                    }
                }
        }
    }

    private fun getGames() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gamesFeedViewModel.gamesResultState.collect { state ->
                    state?.let {
                        gameListPaginationAdapter.submitData(state)
                    }
                }
            }
        }
    }

    private fun searchGames(query: String) {
        gamesFeedViewModel.searchGames(query)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gamesFeedViewModel.gamesResultState.collect { state ->
                    state?.let {
                        gameListPaginationAdapter.submitData(state)
                    }
                }
            }
        }
    }
}





