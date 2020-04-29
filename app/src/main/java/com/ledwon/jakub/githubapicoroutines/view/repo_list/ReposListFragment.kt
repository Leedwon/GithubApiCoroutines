package com.ledwon.jakub.githubapicoroutines.view.repo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gojuno.koptional.Some
import com.google.android.material.snackbar.Snackbar
import com.ledwon.jakub.githubapicoroutines.MainActivity
import com.ledwon.jakub.githubapicoroutines.R
import com.ledwon.jakub.githubapicoroutines.common.toDeferrableString
import com.ledwon.jakub.model.RepoHeader
import kotlinx.android.synthetic.main.fragment_repos_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReposListFragment : Fragment(), ReposAdapter.Listener {

    companion object {
        const val USERNAME = "username"

        fun createBundle(username: String) = bundleOf(USERNAME to username)
    }

    private val viewModel: ReposListViewModel by viewModel {
        parametersOf(
            requireArguments().getString(
                USERNAME
            )
        )
    }

    private val adapter = ReposAdapter(this)
    private val layoutManager by lazy { LinearLayoutManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reposRecycler.adapter = adapter
        reposRecycler.layoutManager = layoutManager
    }

    override fun onStart() {
        super.onStart()

        viewModel.userRepos.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.user.observe(this, Observer { user ->
            Glide.with(this)
                .load(user.avatarUrl)
                .centerCrop()
                .into(avatar)

            (activity as MainActivity).setToolbarTitle(user.login.toDeferrableString())
        })

        viewModel.error.observe(this, Observer {
            if (it is Some) {
                Snackbar.make(
                    requireView(),
                    it.value.get(requireContext()),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(R.string.retry) {
                        viewModel.fetchData()
                    }
                    .show()
            }
        })
    }

    override fun onRepoClick(item: RepoHeader) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
