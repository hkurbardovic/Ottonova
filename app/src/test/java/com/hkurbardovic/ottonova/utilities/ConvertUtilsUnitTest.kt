package com.hkurbardovic.ottonova.utilities

import com.hkurbardovic.ottonova.network.models.TimelineEvent
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertUtilsUnitTest {
    private val timelineEvents1 = listOf(
        TimelineEvent(
            "uuid1",
            "2020-03-15T09:00:00+00:00",
            "displayCategory1",
            "title1",
            "description1",
            "category1"
        )
    )

    private val timelineEvents2 = listOf<TimelineEvent?>()

    private val timelineEvents3 = listOf<TimelineEvent?>(
        null, null, null
    )

    private val timelineEvents4 = listOf(
        TimelineEvent(
            "uuid1",
            "2020-03-15T09:00:00+00:00",
            "displayCategory1",
            "title1",
            "description1",
            "category1"
        ),
        null,
        TimelineEvent(
            "uuid2",
            "2020-03-14T09:00:00+00:00",
            "displayCategory2",
            "title2",
            "description2",
            "category2"
        ),
        TimelineEvent(
            "uuid3",
            null,
            "displayCategory3",
            "title3",
            "description3",
            "category3"
        ),
        TimelineEvent(
            "uuid4",
            "2020-03-14T09:00:00+00:00",
            "displayCategory4",
            "title4",
            "description4",
            "category4"
        ),
        TimelineEvent(
            "uuid5",
            "2020-03-10T09:00:00+00:00",
            "displayCategory5",
            "title5",
            "description5",
            "category5"
        )
    )

    @Test
    fun convertUtils_convertTimelineEvents_isCorrect1() {
        val result = ConvertUtils.convertTimelineEvents(timelineEvents1)
        val firstGroup = result["2020-03-15"]
        val firstGroupFirstChild = firstGroup?.get(0)
        assertEquals("uuid1", firstGroupFirstChild?.uuid)
        assertEquals(1, result.size)
        assertEquals(1, firstGroup?.size)
    }

    @Test
    fun convertUtils_convertTimelineEvents_isCorrect2() {
        val result = ConvertUtils.convertTimelineEvents(timelineEvents2)
        assertEquals(0, result.size)
    }

    @Test
    fun convertUtils_convertTimelineEvents_isCorrect3() {
        val result = ConvertUtils.convertTimelineEvents(timelineEvents3)
        assertEquals(0, result.size)
    }

    @Test
    fun convertUtils_convertTimelineEvents_isCorrect4() {
        val result = ConvertUtils.convertTimelineEvents(timelineEvents4)
        val firstGroup = result["2020-03-15"]
        val firstGroupFirstChild = firstGroup?.get(0)
        assertEquals("uuid1", firstGroupFirstChild?.uuid)
        assertEquals(1, firstGroup?.size)

        val secondGroup = result["2020-03-14"]
        val secondGroupFirstChild = secondGroup?.get(0)
        assertEquals("uuid2", secondGroupFirstChild?.uuid)
        assertEquals(2, secondGroup?.size)

        assertEquals(3, result.size)
    }
}
