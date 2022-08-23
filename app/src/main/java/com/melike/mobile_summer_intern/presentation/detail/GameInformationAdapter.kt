package com.melike.mobile_summer_intern.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melike.mobile_summer_intern.databinding.GameDetailInformationItemBinding
import com.melike.mobile_summer_intern.presentation.model.GameDetailInfoModel

class GameInformationAdapter(val infoList: MutableList<GameDetailInfoModel>) :
    RecyclerView.Adapter<GameInformationAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(val binding: GameDetailInformationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameInformationAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            GameDetailInformationItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameInformationAdapter.ViewHolder, position: Int) {
        val infoItem = infoList[position]
        holder.binding.apply {
            tvInfoTittle.text = infoItem.title
            tvInformationBody.text = infoItem.content
        }
    }

    override fun getItemCount(): Int = infoList.size

}