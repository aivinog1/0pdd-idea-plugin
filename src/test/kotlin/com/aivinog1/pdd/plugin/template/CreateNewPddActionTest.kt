package com.aivinog1.pdd.plugin.template

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixture4TestCase
import io.mockk.*
import org.junit.Test

class CreateNewPddActionTest : LightPlatformCodeInsightFixture4TestCase() {

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
        val fileName = "SimpleJavaClass.java"
        val file = myFixture.configureByFile(fileName)
        every { actionEvent.getData(CommonDataKeys.PSI_FILE) } returns file
        every { actionEvent.getData(CommonDataKeys.EDITOR) } returns myFixture.editor
        every { actionEvent.getData(CommonDataKeys.PROJECT) } returns myFixture.project
        createNewPddAction.actionPerformed(actionEvent)
        verify {
            logger.trace("Current language: Language: JAVA. It's comment is //.")
        }
        myFixture.checkResult(fileName,
                """//package org.test;

class Test {
    private String test;
}
""", false)
        confirmVerified(logger)
    }
}
