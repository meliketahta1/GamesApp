package com.melike.mobile_summer_intern.presentation.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.melike.mobile_summer_intern.databinding.DeleteDialogFragmentBinding
import com.melike.mobile_summer_intern.databinding.FragmentGameDetailBinding
import com.melike.mobile_summer_intern.presentation.feed.bindUIState
import com.melike.mobile_summer_intern.presentation.model.GameDetailInfoModel
import com.melike.mobile_summer_intern.presentation.model.GameDetailResult

import kotlinx.coroutines.launch


class GameDetailFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailBinding
    val args: GameDetailFragmentArgs by navArgs()
    private lateinit var gameInformationAdapter: GameInformationAdapter
    private val gamesDetailViewModel: GameDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesDetailViewModel.initViewModel(args.gameId)
        getStatus()
        initListeners()
        getWishListStatus()
        getGameDetail()
        observeDialogEvent()
    }

    private fun initListeners() {
        binding.tvDescription.setOnClickListener {
            gamesDetailViewModel.changeLineNumber()
        }
        binding.ivBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun getGameDetail() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gamesDetailViewModel.gamesResultState.collect { state ->
                    state?.let {
                        bindInfo(state)
                    }
                }
            }
        }
    }

    private fun getStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gamesDetailViewModel.gamesStatusState.collect { state ->
                    bindState(state)
                }
            }
        }
    }

    private fun bindState(state: GameStatusUIState) {
        binding.pbGameDetail.isVisible = state.isLoading()
        binding.clGameDetail.isVisible = state.isSuccess()
    }

    private fun getWishListStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gamesDetailViewModel.isInWishList.collect { state ->
                    state.let {
                        binding.ivWishList.setImageDrawable(
                            requireContext().getDrawable(state.getWishlistDrawable())
                        )
                    }
                }
            }
        }
    }

    private fun bindInfo(state: GameDetailUiState) {
        val game = state.gameDetailResult
        binding.bindUIState(state)
        initWishListButtonListener(state.gameDetailResult)
        directReddit(game.redditUrl)
        directWebsite(game.website)
        initRecyclerView(state.getInfoList(requireContext()))
    }

    private fun initWishListButtonListener(gameDetailResult: GameDetailResult) {
        binding.ivWishList.setOnClickListener {
            gamesDetailViewModel.handleWishlistOperation(gameDetailResult)
        }
    }

    private fun directReddit(redditUrl: String?) {
        binding.tvGoReddit.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(redditUrl))
            )
        }
    }

    private fun directWebsite(website: String?) {
        binding.tvGoWebsite.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(website))
            )
        }
    }

    private fun initRecyclerView(informationList: MutableList<GameDetailInfoModel>) {
        gameInformationAdapter = GameInformationAdapter(informationList)
        binding.rvGameInfo.adapter = gameInformationAdapter
    }

    private fun observeDialogEvent() {
        lifecycleScope.launchWhenStarted {
            gamesDetailViewModel.dialogActionReceiver.collect {
                showRemoveDialog()
            }
        }
    }

    private fun showRemoveDialog() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DeleteDialogFragmentBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.btnYes.setOnClickListener {
            gamesDetailViewModel.deleteFromWishlist(args.gameId)
            dialog.dismiss()
        }
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}