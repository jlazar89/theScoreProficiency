package com.thescore.proficiencyexercise.ui.memberdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import com.thescore.proficiencyexercise.R
import com.thescore.proficiencyexercise.databinding.FragmentTeamDetailListBinding
import kotlinx.android.synthetic.main.fragment_team_detail_list.*


/**
 * Created Date: 10/27/2020
 * Purpose: Fragment that Team Member details
 */
class TeamDetailFragment : Fragment() {

    private var teamDetailAdapter: TeamDetailAdapter? = null
    private val args: TeamDetailFragmentArgs by navArgs()

    lateinit var binding: FragmentTeamDetailListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_team_detail_list, container, false
        )

        // Initialize the Team adapter along
        teamDetailAdapter = TeamDetailAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dataItem = args.team
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = args.team.full_name


        rvTeamPlayers.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = teamDetailAdapter
        }

        teamDetailAdapter?.setTeamData(args.team.players)

    }

}