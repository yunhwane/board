package com.yh.board.article.application.service

import com.yh.board.annotation.UseCase
import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.application.port.`in`.ArticleCreateUseCase
import com.yh.board.article.application.port.out.SaveArticlePort
import com.yh.board.article.domain.ArticleCreateResult


@UseCase
class ArticleCreateService(
    private val saveArticlePort: SaveArticlePort,
) : ArticleCreateUseCase {
    override fun execute(command: ArticleCreateCommand): ArticleCreateResult {
        return saveArticlePort.save(command)
    }
}