package com.xolary.overwatchaimtrainigresults

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xolary.overwatchaimtrainigresults.data.Item
import com.xolary.overwatchaimtrainigresults.databinding.ItemListItemBinding

class ItemListAdapter(private val onItemClicked: (Item) -> Unit) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListAdapter.ItemViewHolder {
        return ItemViewHolder(
            ItemListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.apply {
                numberOfDay.text = item.numberOfDay.toString()
                difficulty.text = item.opponentDifficulty
                name.text = item.characterName
                spentTime.text = item.spentTime.toString()
                kills.text = item.kills.toString()
                accuracy.text = item.accuracy.toString()
            }
        }
    }

    companion object {
        private var DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.Id == newItem.Id
            }

        }
    }
}