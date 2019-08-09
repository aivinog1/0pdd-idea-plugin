package com.aivinog1.pdd.plugin.template

import com.intellij.lang.LanguageCommenters
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

/**
 * This action creates a new PDD todo.
 * @todo #38:30m Consider moving to the EditorAction. I think, this class would be more suitable for this task.
 */
class CreateNewPddAction(private val logger: Logger = Logger.getInstance("CreateNewPddAction")) : AnAction("Create a new PDD.") {

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.PSI_FILE)
        val editor = e.getData(CommonDataKeys.EDITOR)
        val project = e.getData(CommonDataKeys.PROJECT)
        if (file != null && editor != null && project != null) {
            actionPerformed(file, editor, project)
        }
    }

    private fun actionPerformed(file: PsiFile, editor: Editor, project: Project) {
        val language = file.language
        val commenter = LanguageCommenters.INSTANCE.forLanguage(language)
        val lineCommentPrefix = commenter.lineCommentPrefix
        if (editor.isInsertMode && lineCommentPrefix != null) {
            val document = editor.document
            if (document.isWritable) {
                WriteCommandAction.runWriteCommandAction(project) {
                    // @todo #38:30 For now this action just comment line and nothing more. Needs to implement inserting a todo.
                    document.insertString(0, lineCommentPrefix)
                }
            }
            logger.trace("Current language: $language. It's comment is $lineCommentPrefix.")
        }
    }
}
