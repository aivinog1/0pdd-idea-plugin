package com.aivinog1.pdd.plugin.template

import com.intellij.lang.LanguageCommenters
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiFile

class CreateNewPddAction(private val logger: Logger = Logger.getInstance("CreateNewPddAction")) : AnAction("Create a new PDD.") {

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.PSI_FILE)
        if (file != null) {
            actionPerformed(file)
        }
    }


// @todo #15:30m For now, this method did nothing but only logging the language and the comment symbol.
//  Needs to implement inserting a todo.
    private fun actionPerformed(file: PsiFile) {
        val language = file.language
        val commenter = LanguageCommenters.INSTANCE.forLanguage(language)
        val lineCommentPrefix = commenter.lineCommentPrefix
        logger.trace("Current language: $language. It's comment is $lineCommentPrefix.")
    }
}
