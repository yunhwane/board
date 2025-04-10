package com.yh.board.article.application.service

import com.yh.board.annotation.UseCase
import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.application.port.`in`.ArticleCreateUseCase
import com.yh.board.article.application.port.out.SaveArticlePort
import com.yh.board.article.domain.Article


@UseCase
class ArticleCreateService(
    private val saveArticlePort: SaveArticlePort,
) : ArticleCreateUseCase {
    override fun execute(command: ArticleCreateCommand): Article {
        val article = Article(
            title = command.title,
            content = command.content,
            writerId = command.writerId,
            boardId =  command.boardId,
        )
        return saveArticlePort.save(article)
    }
}