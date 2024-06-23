package com.example.springbootdemo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CommonConstantsTest {
    @Test
    fun `test constant stays the same`() {
        assertEquals(
            "This is a multi-line message to show what happens. This is the second line of such message.",
            MULTI_LINE_MESSAGE
        )
    }
}