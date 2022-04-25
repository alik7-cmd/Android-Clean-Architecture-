package com.techascent.cleanarchitecture2.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.techascent.cleanarchitecture2.R
import com.techascent.cleanarchitecture2.databinding.ItemRepoBinding
import com.techascent.cleanarchitecture2.domain.repo.entity.RepoItemEntity
import javax.inject.Inject

class RepoAdapter (private val listener : OnRepoClickListener<RepoItemEntity>) :
    ListAdapter<RepoItemEntity, RepoAdapterVH>(RepoDiffCallback()) {

    class RepoDiffCallback : DiffUtil.ItemCallback<RepoItemEntity>() {
        override fun areItemsTheSame(oldItem: RepoItemEntity, newItem: RepoItemEntity): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: RepoItemEntity, newItem: RepoItemEntity) =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapterVH {

        return RepoAdapterVH(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoAdapterVH, position: Int) {
        holder.bind(getItem(position), listener)

    }

}

class RepoAdapterVH(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item :RepoItemEntity, listener : OnRepoClickListener<RepoItemEntity>){
        with(binding){
            repoName.text = item.name
            description.text = item.description
            Picasso.get().load(item.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
            root.setOnClickListener {
                listener.onRepoClick(item)
            }
        }
    }

}


interface OnRepoClickListener<T>{
    fun onRepoClick(data : T)
}