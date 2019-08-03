package com.aivinog1.pdd.plugin

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

/**
 * @todo #20:30m This is a dummy test that verify that report working. Needs to remove this or add some cases.
 */
class TestTaskTest {

    val testTask: TestTask = TestTask()

    @Test
    fun test() {
        assertNotNull(testTask)
    }
}
