package utils

import org.testng.Assert
import java.time.Clock

class TimeCompareHelper {
    private val regexLocalTime = "(.{14})$"
    private val regexResponseTime = "(.{8})$"

    fun localToResponseAssert(time: String) {
        val currentTime = Clock.systemUTC().instant().toString().replace(regexLocalTime.toRegex(), "")

        Assert.assertEquals(
            currentTime,
            time.replace(regexResponseTime.toRegex(), ""),
            "Alarm! Время из респонса и локальное отличаются"
        )
    }
}