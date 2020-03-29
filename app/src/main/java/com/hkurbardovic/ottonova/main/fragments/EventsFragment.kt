package com.hkurbardovic.ottonova.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.hkurbardovic.ottonova.adapters.TimelineEventsGroupAdapter
import com.hkurbardovic.ottonova.databinding.FragmentEventsBinding
import com.hkurbardovic.ottonova.main.MainActivity
import com.hkurbardovic.ottonova.main.MainValidator
import com.hkurbardovic.ottonova.main.viewmodels.EventsViewModel
import com.hkurbardovic.ottonova.main.viewmodels.EventsViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EventsFragment : DaggerFragment(), TimelineEventsGroupAdapter.OnChildClickListener,
    MainActivity.NetworkListener {

    private lateinit var binding: FragmentEventsBinding

    private lateinit var adapter: TimelineEventsGroupAdapter

    @Inject
    lateinit var eventsViewModelFactory: EventsViewModelFactory

    private val eventsViewModel: EventsViewModel by viewModels {
        eventsViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val context = context ?: return binding.root

        setAdapter(context)
        setDataBinding()
        observeLiveData()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val context = context ?: return
        (context as MainActivity).registerNetworkListener(this)

        getProfiles()
    }

    override fun onStop() {
        super.onStop()

        val context = context ?: return
        (context as MainActivity).removeNetworkListener()
    }

    override fun onChildClick(title: String) {
        val context = context ?: return
        (context as MainActivity).addFragment(EventDetailsFragment.newInstance(title))
    }

    override fun onAvailable() {
        getProfiles()
    }

    private fun setAdapter(context: Context) {
        adapter = TimelineEventsGroupAdapter(context)
        adapter.setOnChildClickListener(this)
        binding.expandableListView.setAdapter(adapter)
    }

    private fun setDataBinding() {
        binding.eventsViewModel = eventsViewModel
    }

    private fun observeLiveData() {
        eventsViewModel.profilesLiveData.observe(viewLifecycleOwner) {
            MainValidator.getProfileId(it)?.let { profileId ->
                getHealthPrompts(profileId)
                getTimelineEvents(profileId)
            }
        }

        eventsViewModel.healthPromptsLiveData.observe(viewLifecycleOwner) {
            MainValidator.getHealthPrompt(it)?.let { healthPrompt ->
                eventsViewModel.postHealthPrompt(healthPrompt)
            }
        }

        eventsViewModel.timelineEventGroupsMapLiveData.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }

        eventsViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            MainValidator.getErrorMessage(it)?.let { errorMessage ->
                val context = context ?: return@observe
                (context as MainActivity).showMessage(errorMessage)
            }
        }

        eventsViewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.isLoading = it
        }
    }

    private fun getProfiles() {
        eventsViewModel.getProfiles()
    }

    private fun getHealthPrompts(profileId: String) {
        eventsViewModel.getHealthPrompts(profileId)
    }

    private fun getTimelineEvents(profileId: String) {
        eventsViewModel.getTimelineEvents(profileId)
    }

    companion object {
        fun newInstance() = EventsFragment()
    }
}