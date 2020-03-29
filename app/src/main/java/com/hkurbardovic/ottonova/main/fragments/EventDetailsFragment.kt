package com.hkurbardovic.ottonova.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hkurbardovic.ottonova.databinding.FragmentEventDetailsBinding
import dagger.android.support.DaggerFragment

class EventDetailsFragment : DaggerFragment() {

    private lateinit var binding: FragmentEventDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.title = arguments?.getString(TITLE_EXTRA)

        return binding.root
    }

    companion object {
        fun newInstance(title: String): EventDetailsFragment = EventDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(TITLE_EXTRA, title)
            }
        }

        private const val TITLE_EXTRA = "com.hkurbardovic.ottonova.main.fragments.TITLE_EXTRA"
    }
}