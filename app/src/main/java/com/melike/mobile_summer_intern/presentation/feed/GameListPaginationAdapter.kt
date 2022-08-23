package com.melike.mobile_summer_intern.presentation.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.melike.mobile_summer_intern.common.util.loadImage
import com.melike.mobile_summer_intern.databinding.GameListItemBinding
import com.melike.mobile_summer_intern.presentation.model.GameResult
import javax.inject.Inject

class GameListPaginationAdapter @Inject constructor(private val onGameItemClicked: OnGameItemClicked) :
    PagingDataAdapter<GameResult, GameListPaginationAdapter.ViewHolder>(DifferCallBack) {

    private lateinit var context: Context

    inner class ViewHolder(var binding: GameListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameListPaginationAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            GameListItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        holder.binding.apply {
            tvGameName.text = game?.name
            game?.background_image?.let { ivGameItem.loadImage(it) }
        }
        holder.itemView.setOnClickListener {
            onGameItemClicked.onGameItemClicked(game)
        }
    }

    object DifferCallBack : DiffUtil.ItemCallback<GameResult>() {
        override fun areItemsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
            return oldItem == newItem
        }
    }
}