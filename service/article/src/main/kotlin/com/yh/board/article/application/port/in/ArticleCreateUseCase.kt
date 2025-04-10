package com.yh.board.article.application.port.`in`

import com.yh.board.article.domain.ArticleCreateResult

interface ArticleCreateUseCase {
    fun execute(command: ArticleCreateCommand): ArticleCreateResult
}