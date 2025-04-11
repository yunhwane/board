package com.yh.board.article.application.port.out

import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.application.port.`in`.ArticleUpdateCommand
import com.yh.board.article.domain.Article

interface SaveArticlePort {
    fun save(command: ArticleCreateCommand): Article
    fun update(article: Article): Article
    fun delete(articleId: Long)
}