package com.ledwon.jakub.githubapicoroutines.view.query

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ledwon.jakub.githubapicoroutines.R
import com.ledwon.jakub.githubapicoroutines.view.repo_list.ReposListFragment
import kotlinx.android.synthetic.main.fragment_query.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QueryFragment : Fragment() {

    private val viewModel: QueryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_query, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText.addTextChangedListener {
            viewModel.onTextChanged(it.toString())
        }

        search.setOnClickListener {
            viewModel.search()
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.query.observe(this, Observer {
            if (usernameEditText.text.toString() != it) {
                usernameEditText.setTextKeepState(it)
            }
        })

        viewModel.error.observe(this, Observer {
            usernameTextInputLayout.error = it.toNullable()?.get(requireContext())
        })

        viewModel.command.observe(this, Observer {
            when (it) {
                is QueryViewModel.Command.OpenReposFragment ->
                    findNavController().navigate(
                        R.id.action_queryFragment_to_reposListFragment,
                        ReposListFragment.createBundle(it.query)
                    ).also { viewModel.onCommandHandled() }
            }
        })
    }


}