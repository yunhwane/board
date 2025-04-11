package com.yh.board.article.application.service

import com.yh.board.annotation.UseCase
import com.yh.board.article.application.port.`in`.ArticleUpdateCommand
import com.yh.board.article.application.port.`in`.ArticleUpdateUseCase
import com.yh.board.article.application.port.out.LoadArticlePort
import com.yh.board.article.application.port.out.SaveArticlePort
import com.yh.board.article.domain.Article


@UseCase
class ArticleUpdateService(
    private val loadArticlePort: LoadArticlePort,
    private val saveArticlePort: SaveArticlePort,
) : ArticleUpdateUseCase {

    override fun execute(command: ArticleUpdateCommand): Article {
        val article = loadArticlePort.getArticle(command.id)
        article.update(command.title, command.content)
        return saveArticlePort.update(article)
    }

}