package com.hkurbardovic.ottonova.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.hkurbardovic.ottonova.databinding.ItemTimelineEventChildBinding
import com.hkurbardovic.ottonova.databinding.ItemTimelineEventGroupBinding
import com.hkurbardovic.ottonova.main.MainActivity
import com.hkurbardovic.ottonova.main.fragments.EventDetailsFragment
import com.hkurbardovic.ottonova.network.models.TimelineEvent

class TimelineEventsGroupAdapter(private val context: Context) : BaseExpandableListAdapter() {

    private lateinit var onChildClickListener: OnChildClickListener

    private val timelineEventGroups = arrayListOf<String>()

    private val timelineEventGroupsHashMap: HashMap<String, List<TimelineEvent>> = hashMapOf()

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val timelineEventGroup = getGroup(groupPosition)

        val view = if (convertView == null) ItemTimelineEventGroupBinding.inflate(
            LayoutInflater.from(context), parent, false
        ) else convertView.tag as ItemTimelineEventGroupBinding

        view.apply {
            date = timelineEventGroup
        }

        view.root.tag = view

        return view.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val child = getChild(groupPosition, childPosition)

        val view = if (convertView == null) ItemTimelineEventChildBinding.inflate(
            LayoutInflater.from(context), parent, false
        ) else convertView.tag as ItemTimelineEventChildBinding

        view.apply {
            this.timelineEvent = child
            this.clickListener = View.OnClickListener {
                (context as MainActivity).addFragment(
                    EventDetailsFragment.newInstance(child?.title ?: "")
                )
            }
        }

        view.root.tag = view

        return view.root
    }

    override fun getGroup(groupPosition: Int): String {
        return timelineEventGroups[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): TimelineEvent? {
        return timelineEventGroupsHashMap.getOrElse(timelineEventGroups[groupPosition]) { ArrayList() }[childPosition]
    }

    override fun getGroupCount() = timelineEventGroups.size

    override fun getChildrenCount(groupPosition: Int): Int {
        val list =
            timelineEventGroupsHashMap.getOrElse(timelineEventGroups[groupPosition]) { ArrayList() }
        return list.size
    }

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    override fun hasStableIds() = true

    fun addItems(timelineEventGroupsHashMap: Map<String, List<TimelineEvent>>) {
        timelineEventGroups.clear()
        timelineEventGroups.addAll(timelineEventGroupsHashMap.keys)

        this.timelineEventGroupsHashMap.clear()
        this.timelineEventGroupsHashMap.putAll(timelineEventGroupsHashMap)

        notifyDataSetChanged()
    }

    fun setOnChildClickListener(onChildClickListener: OnChildClickListener) {
        this.onChildClickListener = onChildClickListener
    }

    interface OnChildClickListener {
        fun onChildClick(title: String)
    }
}