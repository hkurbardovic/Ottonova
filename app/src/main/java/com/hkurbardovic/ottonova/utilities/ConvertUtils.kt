package com.hkurbardovic.ottonova.utilities

import com.hkurbardovic.ottonova.network.models.TimelineEvent
import java.util.*
import kotlin.collections.LinkedHashMap

object ConvertUtils {

    fun convertTimelineEvents(timelineEvents: List<TimelineEvent?>): LinkedHashMap<String, ArrayList<TimelineEvent>> {
        val linkedHashMap = LinkedHashMap<String, ArrayList<TimelineEvent>>()

        for (timelineEvent in timelineEvents) {
            if (timelineEvent == null) continue
            val timestamp = DateUtils.format(timelineEvent.timestamp) ?: continue

            val list = linkedHashMap[timestamp] ?: arrayListOf()
            list.add(timelineEvent)
            linkedHashMap[timestamp] = list
        }

        return linkedHashMap
    }
}