package com.yh.board.article.application.port.out

import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.domain.ArticleCreateResult

interface SaveArticlePort {
    fun save(command: ArticleCreateCommand): ArticleCreateResult
}