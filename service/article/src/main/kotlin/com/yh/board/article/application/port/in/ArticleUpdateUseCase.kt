package com.yh.board.article.application.port.`in`

import com.yh.board.article.domain.Article

interface ArticleUpdateUseCase {
    fun execute(command: ArticleUpdateCommand): Article
}