package com.hkurbardovic.ottonova.main

import com.hkurbardovic.ottonova.network.models.HealthPrompt
import com.hkurbardovic.ottonova.network.models.Profile
import com.hkurbardovic.ottonova.utilities.Event
import org.junit.Assert.assertEquals
import org.junit.Test

class MainValidatorUnitTest {

    private val profiles1 = Event(listOf(Profile("profile_id")))

    private val profiles2 = null

    private val profiles3 = Event(listOf(Profile(null)))

    private val profiles4 = Event(null)

    private val healthPrompts1 =
        Event(listOf(HealthPrompt("uuid1", "message1", false, "dc1", null, null)))

    private val healthPrompts2 = null

    private val healthPrompts3 = Event(listOf(HealthPrompt(null, null, null, null, null, null)))

    private val healthPrompts4 = Event(null)

    @Test
    fun mainValidator_getProfileId_isCorrect1() {
        val result = MainValidator.getProfileId(profiles1)
        assertEquals("profile_id", result)
    }

    @Test
    fun mainValidator_getProfileId_isCorrect2() {
        val result = MainValidator.getProfileId(profiles2)
        assertEquals(null, result)
    }

    @Test
    fun mainValidator_getProfileId_isCorrect3() {
        val result = MainValidator.getProfileId(profiles3)
        assertEquals(null, result)
    }

    @Test
    fun mainValidator_getProfileId_isCorrect4() {
        val result = MainValidator.getProfileId(profiles4)
        assertEquals(null, result)
    }

    @Test
    fun mainValidator_getHealthPrompt_isCorrect1() {
        val result = MainValidator.getHealthPrompt(healthPrompts1)
        assertEquals("uuid1", result?.uuid)
    }

    @Test
    fun mainValidator_getHealthPrompt_isCorrect2() {
        val result = MainValidator.getHealthPrompt(healthPrompts2)
        assertEquals(null, result)
    }

    @Test
    fun mainValidator_getHealthPrompt_isCorrect3() {
        val result = MainValidator.getHealthPrompt(healthPrompts3)
        assertEquals(null, result?.uuid)
    }

    @Test
    fun mainValidator_getHealthPrompt_isCorrect4() {
        val result = MainValidator.getHealthPrompt(healthPrompts4)
        assertEquals(null, result)
    }

    @Test
    fun mainValidator_getErrorMessage_isCorrect1() {
        val result = MainValidator.getErrorMessage(Event("error"))
        assertEquals("error", result)
    }

    @Test
    fun mainValidator_getErrorMessage_isCorrect2() {
        val result = MainValidator.getHealthPrompt(Event(null))
        assertEquals(null, result)
    }
}