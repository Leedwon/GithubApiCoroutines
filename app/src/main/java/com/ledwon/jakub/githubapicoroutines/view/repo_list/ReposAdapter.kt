package com.ledwon.jakub.githubapicoroutines.view.repo_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ledwon.jakub.githubapicoroutines.R
import com.ledwon.jakub.model.RepoHeader
import kotlinx.android.synthetic.main.item_repo.view.*

class ReposAdapter(private val listener: Listener) : ListAdapter<RepoHeader, ReposAdapter.Holder>(
    object : DiffUtil.ItemCallback<RepoHeader>() {
        override fun areItemsTheSame(oldItem: RepoHeader, newItem: RepoHeader): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RepoHeader, newItem: RepoHeader): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        holder.itemView.repoName.text = item.name
        holder.itemView.setOnClickListener {
            listener.onRepoClick(item)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener {
        fun onRepoClick(item: RepoHeader)
    }
}