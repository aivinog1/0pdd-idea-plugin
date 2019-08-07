package com.aivinog1.pdd.plugin.template

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixture4TestCase
import io.mockk.*
import org.junit.Test

class CreateNewPddActionTest: LightPlatformCodeInsightFixture4TestCase() {

    private val logger: Logger = mockk()
    private val createNewPddAction = CreateNewPddAction(logger = logger)

    override fun getTestDataPath(): String {
        return "testData"
    }

    @Test
    fun `test that simple java file has comment`() {
        every { logger.trace("Current language: Language: JAVA. It's comment is //.") } just Runs
        // @todo #15:30m After CreateNewPddAction became registered let's drop this mock and invite action via the IntelliJ platform.
        val actionEvent: AnActionEvent = mockk()
        every { actionEvent.getData(CommonDataKeys.PSI_FILE) } returns myFixture.configureByFile("SimpleJavaClass.java")
        createNewPddAction.actionPerformed(actionEvent)
        verify {
            logger.trace("Current language: Language: JAVA. It's comment is //.")
        }
        confirmVerified(logger)
    }
}
