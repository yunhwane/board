package com.yh.board.article.application.port.`in`

import com.yh.board.article.domain.Article

interface ArticleCreateUseCase {
    fun execute(command: ArticleCreateCommand): Article
}