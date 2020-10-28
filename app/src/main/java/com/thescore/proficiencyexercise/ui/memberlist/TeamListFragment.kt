package com.thescore.proficiencyexercise.ui.memberlist

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.thescore.proficiencyexercise.R
import com.thescore.proficiencyexercise.data.entity.TeamResponse
import com.thescore.proficiencyexercise.utils.*
import com.thescore.proficiencyexercise.widgets.listeners.ItemClickHandler
import kotlinx.android.synthetic.main.fragment_team_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class TeamListFragment : Fragment() {

    // viewmodel instance provided by Koin
    private val teamListViewModel by viewModel<TeamListViewModel>()
    private var teamListAdapter: TeamListAdapter? = null
    private var sortBy: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        teamListViewModel.apply {
            loadList(Constants.Tag.LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_team_list, container, false)

        // Initialize the Team adapter along
        teamListAdapter = TeamListAdapter(object : ItemClickHandler {
            override fun onItemClick(view: View, item: TeamResponse) {
                val players =
                    TeamListFragmentDirections.actionTeamListFragmentToTeamDetailFragment(item)
                findNavController().navigate(players)

            }
        })

        teamListViewModel.apply {

            // Observing the changes made into the state liveData instance
            mState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    is LoadingState -> {
                        loader.visibility = View.VISIBLE
                        rvItems.visibility = View.INVISIBLE
                    }
                    is EmptyState -> {
                        emptyView.visibility = View.VISIBLE
                        rvItems.visibility = View.INVISIBLE
                    }
                    is ErrorState -> {
                        loader.visibility = View.GONE
                        emptyView.visibility = View.VISIBLE
                        emptyView.text = "Error occured"
                    }
                    is SuccessState -> {
                        loader.visibility = View.INVISIBLE
                        rvItems.visibility = View.VISIBLE
                    }

                }
            })

            //Observer for List
            getTeamList().observe(viewLifecycleOwner, Observer {
                // observing the changes made into the list and set the updated data to the adapter of recyclerview.
                teamListAdapter?.setTeamData(it)

            })
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvItems.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = teamListAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_team_list, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.name_asc -> {
                sortBy = 0
                teamListViewModel.sortBy(sortBy)
                true
            }
            R.id.name_desc -> {
                sortBy = 1
                teamListViewModel.sortBy(sortBy)
                true
            }
            R.id.wins_asc -> {
                sortBy = 2
                teamListViewModel.sortBy(sortBy)
                true
            }
            R.id.wins_desc -> {
                sortBy = 3
                teamListViewModel.sortBy(sortBy)
                true
            }
            R.id.losses_asc -> {
                sortBy = 4
                teamListViewModel.sortBy(sortBy)
                true
            }
            R.id.losses_desc -> {
                sortBy = 5
                teamListViewModel.sortBy(sortBy)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}